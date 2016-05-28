package titopetri.com.agendadecontatos;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle = intent.getExtras();
        Object[] mensagens = (Object[]) bundle.get("pdus");

        byte[] mensagem = (byte[]) mensagens[0];

        SmsMessage msg = SmsMessage.createFromPdu(mensagem);
        String telefone = msg.getDisplayOriginatingAddress();

        ContatoDAO dao = new ContatoDAO(context);

        if (dao.isContato(telefone)) {
            MediaPlayer player = MediaPlayer.create(context, R.raw.msg);
            player.start();
        }
        Toast.makeText(context, "Novo SMS", Toast.LENGTH_LONG).show();
    }
}
