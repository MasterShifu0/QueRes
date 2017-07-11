package com.example.shekhar.queres;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;
import java.util.zip.Inflater;

/**
 * Created by Shekhar on 10/07/17.
 */

public class ShowResults extends Activity {
    List<String> queryList;
    ListView resultList;
    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_results);
        resultList = (ListView) findViewById(R.id.result_container);
        queryList = new SavePreferences(this).getStringSetPref();
        //resultList.setAdapter(new ResultAdapter(this, queryList));
        searchforResults();
        finish();
    }

    //This will search for only first query result
    private void searchforResults() {

        List<String> queryList = new SavePreferences(this).getStringSetPref();
        if(queryList != null && queryList.size() > 0){
            Log.i(TAG, "Searching for results " + queryList.get(0));
            Intent searchIntent = new Intent(Intent.ACTION_WEB_SEARCH);
            searchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            searchIntent.putExtra(SearchManager.QUERY, queryList.get(0));
            this.startActivity(searchIntent);
        }
    }

    class ResultAdapter extends BaseAdapter{
        private List<String> ql;
        private Context ctx;
        private List<String> ans;

        ResultAdapter(Context context, List<String> stringList){
            ctx = context;
            ql = stringList;
            ans = new ArrayList<String>();
            ans.add("one");
            ans.add("two");
            ans.add("three");
            ans.add("four");
            ans.add("five");
        }

        @Override
        public int getCount() {
            return ql.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View rowView = convertView;
            LayoutInflater inflater;
            ViewHolder holder = new ViewHolder();
            if(convertView == null){
                inflater = (LayoutInflater)ctx.getSystemService(LAYOUT_INFLATER_SERVICE);
                rowView = inflater.inflate(R.layout.result_row, null);
                holder.query = (TextView)rowView.findViewById(R.id.question);
                holder.result = (TextView)rowView.findViewById(R.id.answer);

                rowView.setTag(holder);
            }
            else
                holder = (ViewHolder)rowView.getTag();

            holder.query.setText(ql.get(position));
            holder.result.setText(ans.get(position));

            return rowView;
        }

       public class ViewHolder{
            public TextView query;
            public TextView result;

        }
    }
}
