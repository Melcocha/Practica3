package com.example.practica3;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static String JSON_URL = "https://run.mocky.io/v3/adb0129a-514d-414a-a79e-08b984d53304";


    List<CatalogoModelClas> catalogoList;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        catalogoList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);

        GetData getdata = new GetData();
        getdata.execute(JSON_URL);

    }

    public class GetData extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {

            String current ="";
            try {
                URL url;
                HttpURLConnection urlConnection = null;

                try {
                    url = new URL(JSON_URL);
                    urlConnection = (HttpURLConnection) url.openConnection();

                    InputStream is = urlConnection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);

                    int data = isr.read();
                    while (data != -1){
                        current += (char) data;
                        data = isr.read();
                    }
                    return current;


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (urlConnection != null){
                        urlConnection.disconnect();

                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return current;
        }
        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("recursos");
                Log.d("MyTag", "JSONArray: " + jsonArray.toString());
                for (int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                    CatalogoModelClas model = new CatalogoModelClas();
                    model.setId(jsonObject1.getString("id"));
                    model.setTitulo(jsonObject1.getString("titulo"));
                    model.setDescripcion(jsonObject1.getString("descripcion"));
                    model.setUrl(jsonObject1.getString("url"));
                    model.setArticulo(jsonObject1.getString("articulo"));

                    catalogoList.add(model);

                }


            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            PutDataIntoRecyclerView(catalogoList);
            Log.d("Myadapter", "JSONArray: " + catalogoList.toString());
        }
    }

    private void PutDataIntoRecyclerView(List<CatalogoModelClas> catalogoList) {
        Adaptery adaptery = new Adaptery(this,catalogoList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptery);

    }

}