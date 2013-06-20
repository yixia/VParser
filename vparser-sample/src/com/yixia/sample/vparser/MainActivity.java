package com.yixia.sample.vparser;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yixia.vparser.VParser;
import com.yixia.vparser.model.Video;

public class MainActivity extends Activity implements OnClickListener {

	private VParser mVParser;
	private EditText mEditText;
	private Button mParserButton;
	private TextView mTitleView;
	private TextView mUriView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mVParser = new VParser(this);
		mEditText = (EditText)findViewById(R.id.et_website);
		mParserButton = (Button)findViewById(R.id.bt_parser);
		mParserButton.setOnClickListener(this);
		mTitleView = (TextView)findViewById(R.id.tv_title);
		mUriView = (TextView)findViewById(R.id.tv_uri);
	}

	@Override
  public void onClick(View v) {
		String website = mEditText.getText().toString();
		if (TextUtils.isEmpty(website)) {
			return;
		}
		new AsyncTask<Object, Void, Video>() {

			@Override
      protected Video doInBackground(Object... params) {
	      return mVParser.parse(String.valueOf(params[0]));
      }
			
			@Override
			protected void onPostExecute(Video result) {
			  super.onPostExecute(result);
			  String title = result.title;
			  String uri = result.videoUri;
			  String website = result.videoSiteUri;
			  mTitleView.setText(title);
			  mUriView.setText(uri);
			}
			
		}.execute(website);
	  
  }
	
	

}
