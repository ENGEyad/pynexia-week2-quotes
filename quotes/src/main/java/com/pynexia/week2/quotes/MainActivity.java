package com.pynexia.week2.quotes;
import android.content.Intent;
import android.content.ActivityNotFoundException;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;
public class MainActivity extends BaseActivity {
    private String[] quotes;
    private int current;
    private final Random random = new Random();
    @Override public void onCreate(Bundle state) {
        super.onCreate(state); showLayout(R.layout.activity_main);
        quotes = getResources().getStringArray(R.array.quotes);
        current = state == null ? getPreferences(MODE_PRIVATE).getInt("quote", 0) : state.getInt("quote", 0);
        if (current < 0 || current >= quotes.length) current = 0;
        render();
        findViewById(R.id.next).setOnClickListener(v -> { current = QuoteDeck.next(current, quotes.length, random); render(); });
        findViewById(R.id.share).setOnClickListener(v -> {
            Intent send = new Intent(Intent.ACTION_SEND).setType("text/plain").putExtra(Intent.EXTRA_TEXT, quotes[current] + "\n- " + getString(R.string.attribution));
            try { startActivity(Intent.createChooser(send, getString(R.string.share))); }
            catch (ActivityNotFoundException error) { Toast.makeText(this, R.string.no_share, Toast.LENGTH_LONG).show(); }
        });
    }
    private void render() {
        ((TextView)findViewById(R.id.quote)).setText(quotes[current]);
        ((TextView)findViewById(R.id.counter)).setText(getString(R.string.quote_count, current + 1, quotes.length));
        getPreferences(MODE_PRIVATE).edit().putInt("quote", current).apply();
    }
    @Override protected void onSaveInstanceState(Bundle out) { super.onSaveInstanceState(out); out.putInt("quote", current); }
}
