package com.aiprog.emojimaster;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.provider.FontRequest;
import androidx.emoji.bundled.BundledEmojiCompatConfig;
import androidx.emoji.text.EmojiCompat;
import androidx.emoji.text.FontRequestEmojiCompatConfig;
import androidx.emoji.widget.EmojiAppCompatEditText;
import androidx.emoji.widget.EmojiAppCompatTextView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    /** Change this to {@code false} when you want to use the downloadable Emoji font. */
    private static final boolean USE_BUNDLED_EMOJI = false;

    private EmojiAppCompatEditText editText;
    private EmojiAppCompatTextView textView;
    private CustomTextView textViewCustom;
    private TextView textViewDefault;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setUpEmoji();
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.emoji_edit_text);
        textView = findViewById(R.id.emoji_text_view);
        textViewCustom = findViewById(R.id.emoji_text_view_custom);
        textViewDefault = findViewById(R.id.emoji_text_view_default);


    }

    private void setUpEmoji() {
        final EmojiCompat.Config config;
        if (USE_BUNDLED_EMOJI) {
            // Use the bundled font for EmojiCompat
            config = new BundledEmojiCompatConfig(getApplicationContext());
        } else {
            // Use a downloadable font for EmojiCompat
            final FontRequest fontRequest = new FontRequest(
                    "com.google.android.gms.fonts",
                    "com.google.android.gms",
                    "Noto Color Emoji Compat",
                    R.array.com_google_android_gms_fonts_certs);
            config = new FontRequestEmojiCompatConfig(getApplicationContext(), fontRequest);
        }

        config.setReplaceAll(true)
                .registerInitCallback(new EmojiCompat.InitCallback() {
                    @Override
                    public void onInitialized() {
                        Log.i(TAG, "EmojiCompat initialized");
                        Toast.makeText(MainActivity.this, "EmojiCompat initialized\"",
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailed(@Nullable Throwable throwable) {
                        Log.e(TAG, "EmojiCompat initialization failed", throwable);
                        Toast.makeText(MainActivity.this, "EmojiCompat initialization failed",
                                Toast.LENGTH_SHORT).show();
                    }
                });

        EmojiCompat.init(config);
    }

    public void onClick(View view) {
        if(view.getId() == R.id.emoji_button_ok){
            String s = editText.getText().toString();
            textView.setText(s);
            textViewCustom.setText(s);
            textViewDefault.setText(s);
            editText.setText("");
        }
        else if(view.getId() == R.id.emoji_button){
            //Do something...
        }
    }
}
