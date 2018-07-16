package com.spandana.firebaselogin.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.spandana.firebaselogin.R;

import org.json.JSONArray;
import org.json.JSONObject;

public class TopUpReportDetailsAdapter extends RecyclerView.Adapter<TopUpReportDetailsAdapter.ViewHolder> {

    Context context;
    JSONArray jsonArray = new JSONArray();

    public TopUpReportDetailsAdapter(Context context, JSONArray jsonArray) {

        this.context = context;
        this.jsonArray = jsonArray;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        Context context;
        TextView adminTextView, userTextView;

        public ViewHolder(View view) {
            super(view);
            context = itemView.getContext();

/*            adminTextView = (TextView) itemView.findViewById(R.id.adminTextView);
            userTextView = (TextView) itemView.findViewById(R.id.userTextView);*/

        }
    }

    @Override
    public TopUpReportDetailsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.topup_report_item_layout, parent, false);


        return new TopUpReportDetailsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TopUpReportDetailsAdapter.ViewHolder holder, final int position) {

        try {

            JSONObject object = jsonArray.getJSONObject(position);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return jsonArray.length();
    }
}
