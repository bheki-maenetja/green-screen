package com.example.greenscreen.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.greenscreen.AfterLogin;
import com.example.greenscreen.R;
import com.example.greenscreen.Scan.Pop;
import com.example.greenscreen.Scan.ScanBarcode;
import com.example.greenscreen.yourGreenscore.calculateDate;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;


import java.util.Arrays;
import java.util.List;

/**
 * Demonstrate Firebase Authentication using a Google ID Token.
 */
public class GoogleSignInJava extends Activity implements View.OnClickListener {

    SignInButton signInButton;
    Button guest;
    private static final String TAG = "GoogleActivity";
    private static final int RC_SIGN_IN = 9001;
    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    private GoogleSignInClient mGoogleSignInClient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // [START config_signin]
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        /*if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            startActivity(new Intent(this,GoogleSignInJava.class));
            this.finish();
        }*/
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        // [END config_signin]

        // [START initialize_auth]
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

        signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(this);

        guest = (Button) findViewById(R.id.login);
        guest.setOnClickListener(this::onClick);
    }
   /* public void handleLoginRegister(View view){
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build()
        );
        Intent intent = AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(providers).setTosAndPrivacyPolicyUrls("https://example.com","https://example.com")
                .setLogo(R.drawable.ic_launcher_logo_final_background)

                .build();

        startActivityForResult(intent,RC_SIGN_IN);
    }

    */
    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
    // [END on_start_check_user]

    // [START onactivityresult]
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                startActivity(new Intent(GoogleSignInJava.this, AfterLogin.class));
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                String name = account.getDisplayName();
                Toast.makeText(getApplicationContext(),"Hello, " + name,1).show();
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
            }
        }
    }
    // [END onactivityresult]

    // [START auth_with_google]
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            updateUI(null);
                        }
                    }
                });
    }
    // [END auth_with_google]

    // [START signin]
    public void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    // [END signin]

    private void handleSignInResult(GoogleSignInResult result){
        Log.d(TAG,"handleSignInResult"+ result.isSuccess());
        if(result.isSuccess())
        {
            GoogleSignInAccount acct = result.getSignInAccount();
            Toast.makeText(getApplicationContext(),"Hello, " , Integer.parseInt(acct.getDisplayName())).show();
            startActivity(new Intent(GoogleSignInJava.this, AfterLogin.class));
        }
        else{

        }
    }

    private void updateUI(FirebaseUser user) {

    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.sign_in_button:
                    signIn();
                    break;
            case R.id.login:
                startActivity(new Intent(GoogleSignInJava.this, AfterLogin.class));
        }
    }
}