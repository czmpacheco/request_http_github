package com.czmp.request_github_user;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.czmp.request_github_user.R;

public class MainActivity extends AppCompatActivity {

    private TextView name;
    private TextView username;
    private TextView location;
    private TextView company;
    private TextView email;
    private TextView bio;
    private ImageView image;
    private ProgressDialog load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadUser download = new DownloadUser();

        name = (TextView)findViewById(R.id.textView5);
        username = (TextView)findViewById(R.id.textView11);
        location = (TextView)findViewById(R.id.textView8);
        company = (TextView)findViewById(R.id.textView7);
        email = (TextView)findViewById(R.id.textView4);
        bio = (TextView)findViewById(R.id.textView3);
        image = (ImageView)findViewById(R.id.imageView);

        download.execute();
    }

    private class DownloadUser extends AsyncTask<Void, Void, GitHub> {

        @Override
        protected void onPreExecute(){
            load = ProgressDialog.show(MainActivity.this,
                    "Loading ...", "Gathering information...");
        }

        @Override
        protected GitHub doInBackground(Void... params) {
            Controller util = new Controller();
            return util.getInfo("https://api.github.com/users/czmpacheco");
        }

        @Override
        protected void onPostExecute(GitHub user){
            name.setText(user.getName());
            username.setText(user.getUsername());
            location.setText(user.getLocation());
            email.setText(user.getEmail());
            company.setText(user.getCompany());
            email.setText(user.getEmail());
            bio.setText(user.getBio());
            image.setImageBitmap(user.getImage());
            load.dismiss();
        }
    }
}
