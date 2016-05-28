package titopetri.com.agendadecontatos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class FormularioHelper {

    private Contato contato;

    private EditText nome;
    private EditText email;
    private EditText telefone;
    private EditText endereco;
    private EditText site;

    private ImageView imagemContato;

    private Button botaoFoto;

    public FormularioHelper(Formulario activity) {

        this.contato = new Contato();
        this.nome = (EditText) activity.findViewById(R.id.nomeForm);
        this.email = (EditText) activity.findViewById(R.id.emailForm);
        this.site = (EditText) activity.findViewById(R.id.siteForm);
        this.telefone = (EditText) activity.findViewById(R.id.telefoneForm);
        this.endereco = (EditText) activity.findViewById(R.id.enderecoForm);
        this.imagemContato = (ImageView) activity.findViewById(R.id.imagemForm);
        this.botaoFoto = (Button) activity.findViewById(R.id.botaoFotoForm);
    }

    public Button getBotaoFoto(){
        return botaoFoto;
    }

    public Contato pegaContatoDoFormulario(){
        contato.setNome(nome.getText().toString());
        contato.setEndereco(endereco.getText().toString());
        contato.setEmail(email.getText().toString());
        contato.setSite(site.getText().toString());
        contato.setTelefone(telefone.getText().toString());
        contato.setFoto((String) imagemContato.getTag());
        return contato;
    }



    public void colocaNoFormulario(Contato contato) {
        nome.setText(contato.getNome());
        endereco.setText(contato.getEndereco());
        email.setText(contato.getEmail());
        site.setText(contato.getSite());
        telefone.setText(contato.getTelefone());
        imagemContato.setTag(contato.getFoto());
        carregaImagem(contato.getFoto());
        this.contato = contato;
    }

    public void carregaImagem(String localArquivoFoto) {
        if(localArquivoFoto != null) {
            Bitmap imagemFoto = BitmapFactory.decodeFile(localArquivoFoto);
            imagemContato.setImageBitmap(imagemFoto);
            imagemContato.setTag(localArquivoFoto);
        }
    }
}
