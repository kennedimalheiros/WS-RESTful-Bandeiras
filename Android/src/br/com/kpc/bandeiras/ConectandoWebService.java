package br.com.kpc.bandeiras;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;




import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class ConectandoWebService extends AsyncTask<String, Void, String> {

	private TextView tvUrl;
	private Button btcarregarIMG;
	private Button btCarregarURL;

	//Metodo recebe os parametros.
	public ConectandoWebService(TextView tvUrl, Button btcarregarIMG,
			Button btCarregarURL) {
		super();
		this.tvUrl = tvUrl;
		this.btcarregarIMG = btcarregarIMG;
		this.btCarregarURL = btCarregarURL;
	}

	//Executa antes de consultar WS
	@Override
	protected void onPreExecute() {
		btcarregarIMG.setEnabled(false);
		btCarregarURL.setEnabled(false);
		super.onPreExecute();
	}
	//Executa depois de consultar WS
	@Override
	protected void onPostExecute(String result) {

		tvUrl.setText(result);
		btcarregarIMG.setEnabled(true);
		btCarregarURL.setEnabled(true);
		super.onPostExecute(result);
	}

	//Executando consulta WS
	@Override
	protected String doInBackground(String... params) {
		//Primeiro parametro
		String sigla = params[0];

		try {

			URL url = new URL(
					"http://107.170.184.230:8080/WSBandeira/rest/wsbandeira/getBandeira/"
							+ sigla);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream()));
			return in.readLine();
			 
		} catch (IOException e) {
			Log.e("Erro WebService", "Retorno URL");
			e.printStackTrace();
		}

		return null;
	}

}
