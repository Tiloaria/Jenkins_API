package com.example.tiloaria.geturl;

        import java.io.BufferedReader;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.net.URL;
        import java.net.URLConnection;

        import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.net.Uri;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.provider.MediaStore;
        import android.text.Html;
        import android.util.Log;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.widget.Toast;

        import org.apache.http.HttpResponse;
        import org.apache.http.client.methods.HttpGet;
        import org.apache.http.impl.client.DefaultHttpClient;

        import retrofit.client.Response;
        import retrofit.http.GET;


public class MainActivity extends Activity {
    public volatile String text = "";

    private class DownloadWebPageTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            String response = "";
            for (String url : urls) {
                DefaultHttpClient client = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(url);
                try {
                    HttpResponse execute = client.execute(httpGet);
                    InputStream content = execute.getEntity().getContent();

                    BufferedReader buffer = new BufferedReader(
                            new InputStreamReader(content));
                    String s = "";
                    while ((s = buffer.readLine()) != null) {
                        response += s;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            text = Html.fromHtml(result).toString();
        }
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        (findViewById(R.id.btnWeb)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Context my_context = getApplicationContext();
                    CharSequence text1 = "Hello world!";
                    int duration = Toast.LENGTH_SHORT;
                    new DownloadWebPageTask().execute("https://ci.jenkins.io/");
                    Toast.makeText(my_context, text, duration).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                /*startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.ya.ru")));*/
            }
        });
    }
}
