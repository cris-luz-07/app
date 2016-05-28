package titopetri.com.agendadecontatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ContatoDAO extends SQLiteOpenHelper {

    private static final int VERSAO = 1;
    private static final String TABELA = "Contatos";
    private static final String DATABASE =  "DadosAgenda";

    public ContatoDAO(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    public void inserirContato (Contato contato){

        ContentValues values = new ContentValues();

        values.put("nome", contato.getNome());
        values.put("email", contato.getEmail());
        values.put("site", contato.getSite());
        values.put("telefone", contato.getTelefone());
        values.put("endereco", contato.getEndereco());
        values.put("caminhoFoto", contato.getFoto());

        getWritableDatabase().insert(TABELA, null, values);
    }

    public void apagarContato (Contato contato){
        SQLiteDatabase db = getWritableDatabase();
        String[] args = {contato.getId().toString()};
        db.delete("contatos", "id=?", args);
    }

    public void alteraContato(Contato contato){

        ContentValues values = new ContentValues();

        values.put("nome", contato.getNome());
        values.put("email", contato.getEmail());
        values.put("site", contato.getSite());
        values.put("telefone", contato.getTelefone());
        values.put("endereco", contato.getEndereco());
        values.put("caminhoFoto", contato.getFoto());

        String[] idParaSerAlterado = {contato.getId().toString()};

        getWritableDatabase().update(TABELA, values, "id=?", idParaSerAlterado);
    }

    public void onCreate(SQLiteDatabase db) {
        String ddl = "CREATE TABLE " + TABELA
                + " (id INTEGER PRIMARY KEY, "
                + " nome TEXT NOT NULL, "
                + " telefone TEXT, "
                + " endereco TEXT, "
                + " email TEXT, "
                + " site TEXT, "
                + " caminhoFoto TEXT);";
        db.execSQL(ddl);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion==1) {
            String sql = "ALTER TABLE " + TABELA + " ADD COLUMN caminhoFoto TEXT;";
            db.execSQL(sql);
        }
    }


    public List<Contato> getLista(){

        List<Contato> contatos = new ArrayList<Contato>();
        Cursor c = getReadableDatabase().rawQuery("SELECT * FROM " + TABELA + ";", null);

        while ((c.moveToNext())) {
            Contato contato = new Contato();
            contato.setId(c.getLong(c.getColumnIndex("id")));
            contato.setNome(c.getString(c.getColumnIndex("nome")));
            contato.setTelefone(c.getString(c.getColumnIndex("telefone")));
            contato.setEndereco(c.getString(c.getColumnIndex("endereco")));
            contato.setEmail(c.getString(c.getColumnIndex("email")));
            contato.setSite(c.getString(c.getColumnIndex("site")));
            contato.setFoto(c.getString(c.getColumnIndex("caminhoFoto")));
            contatos.add(contato);
        }
        c.close();
        return contatos;
    }

    public boolean isContato(String telefone){
        String[] parametros = {telefone};
        Cursor rawQuery = getReadableDatabase().rawQuery("SELECT telefone FROM " + TABELA + " WHERE telefone = ?", parametros);
        int total = rawQuery.getCount();
        return total > 0;
    }
}




