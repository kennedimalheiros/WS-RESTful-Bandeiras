package br.com.kpc.bandeiras;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.message.BufferedHeader;

import android.renderscript.Script.FieldBase;
import android.support.v7.app.ActionBarActivity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Bandeiras extends ActionBarActivity implements Runnable {

	private Handler handler;
	private Thread thread;
	private ImageView imgBandeira;
	private Drawable imgTemp;
	private Button btcarregarIMG;
	private Button btCarregarURL;
	private TextView tvUrl;
	private EditText etSigla;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bandeiras);

		handler = new Handler();

		// Referenciando
		tvUrl = (TextView) findViewById(R.id.tvURL);
		imgBandeira = (ImageView) findViewById(R.id.imgBandeira);
		etSigla = (EditText) findViewById(R.id.etSigla);
		btcarregarIMG = (Button) findViewById(R.id.btCarregarIMG);
		btCarregarURL = (Button) findViewById(R.id.btCarregarURL);

	}

	public void btConsumirWs(View v) {
		if (etSigla.getText().toString().trim().equals("")) {
			Toast.makeText(getApplicationContext(), "Favor informe uma sigla!",
					Toast.LENGTH_LONG).show();
			etSigla.setFocusable(true);
			return;
		}
		ConectandoWebService conWS = new ConectandoWebService(tvUrl,
				btcarregarIMG, btCarregarURL);
		conWS.execute(etSigla.getText().toString().trim());

	}

	public void btCarregarImg(View v) {
		if (tvUrl.getText().toString().trim().equals("")) {
			Toast.makeText(getApplicationContext(), "Favor Busque a URL!",
					Toast.LENGTH_LONG).show();
			etSigla.setFocusable(true);
			return;
		}

		thread = new Thread(Bandeiras.this);
		thread.start();

	}

	public void run() {

		try {
			InputStream is = (InputStream) new java.net.URL(tvUrl.getText()
					.toString().trim()).getContent();

			imgTemp = Drawable.createFromStream(is, "src name");

			handler.post(new Runnable() {

				public void run() {
					imgBandeira.setImageDrawable(imgTemp);
					imgBandeira.setVisibility(0);
				}
			});

		} catch (MalformedURLException e) {

			Log.e("Erro Url", "Erro Url IMG", e);
		} catch (IOException e) {

			Log.e("Erro IO", "Erro IMG", e);
		}

	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bandeiras, menu);
		return true;
	}
}
