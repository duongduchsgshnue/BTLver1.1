package com.example.pokemongo1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.parse.ParseUser;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private String id,name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        callbackManager=CallbackManager.Factory.create();
        loginButton=(LoginButton)findViewById(R.id.login_button);
        setLoginButton();

    }

    private void setLoginButton() {
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("Login","Success!");
                Intent intent = new Intent();
                intent.setClass(Login.this, MapsActivity.class);
                startActivity(intent);
                loginButton.setReadPermissions("public_profile");
                resuilt();
                ParseUser.getCurrentUser().setUsername(name);
                ParseUser.getCurrentUser().put("id",id);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    private void resuilt() {
        GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                Log.d("JSON",response.getJSONObject().toString());
                try {
                    id=object.getString("id");
                    name=object.getString("name");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields","name,id");
        graphRequest.setParameters(parameters);
        graphRequest.executeAsync();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    protected void onStart(){
        LoginManager.getInstance().logOut();
        super.onStart();
    }
}
