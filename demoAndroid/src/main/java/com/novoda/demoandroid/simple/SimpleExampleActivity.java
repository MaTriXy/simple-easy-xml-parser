package com.novoda.demoandroid.simple;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.novoda.demoandroid.ParsingTask;
import com.novoda.demoandroid.R;
import com.novoda.demoandroid.SecondLevelBaseActivity;
import com.novoda.sexp.Instigator;
import com.novoda.sexp.RootTag;
import com.novoda.sexp.SimpleEasyXmlParser;
import com.novoda.sexp.SimpleTagInstigator;
import com.novoda.sexp.finder.ElementFinder;
import com.novoda.sexp.finder.ElementFinderFactory;
import com.novoda.sexp.parser.ParseFinishWatcher;

public class SimpleExampleActivity extends SecondLevelBaseActivity {
    // language=XML
    private static final String XML = "<novoda>"
            + "<favouriteColour>Blue</favouriteColour>" + "</novoda>";
    private static ElementFinder<String> elementFinder;

    private TextView parsingResult;
    private ProgressBar progressBar;
    private LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_parsing);

        parsingResult = (TextView) findViewById(R.id.tv_result);
        progressBar = (ProgressBar) findViewById(R.id.pb_oneTag);
        container = (LinearLayout) findViewById(R.id.ll_oneTag);

        ElementFinderFactory factory = SimpleEasyXmlParser
                .getElementFinderFactory();
        elementFinder = factory.getStringFinder();
        Instigator instigator = new SimpleInstigator(
                elementFinder,
                finishWatcher
        );

        new ParsingTask(XML, instigator).execute();
    }

    private ParseFinishWatcher finishWatcher = new ParseFinishWatcher() {
        @Override
        public void onFinish() {
            runOnUiThread(
                    new Runnable() {
                        @Override
                        public void run() {
                            parsingResult.setText(elementFinder.getResultOrThrow());
                            container.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                        }
                    }
            );
        }
    };

    public static class SimpleInstigator extends SimpleTagInstigator {

        public SimpleInstigator(ElementFinder<?> elementFinder, ParseFinishWatcher parseFinishWatcher) {
            super(elementFinder, "favouriteColour", parseFinishWatcher);
        }

        @Override
        public RootTag getRootTag() {
            return RootTag.create("novoda");
        }
    }
}
