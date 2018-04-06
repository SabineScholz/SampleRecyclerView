package com.example.android.samplerecyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerAdapter extends Adapter<RecyclerAdapter.WordViewHolder> {

    private static final String LOG_TAG = RecyclerAdapter.class.getSimpleName() + " TEST";
    final private ItemClickListener onClickListener;
    private int num_view_holders = 0;
    private List<String> model;

    public RecyclerAdapter(List<String> model, ItemClickListener itemClickListener) {
        this.model = model;
        this.onClickListener = itemClickListener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(LOG_TAG, "onCreateViewHolder");

        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item, parent, false);

        return new WordViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        Log.d(LOG_TAG, "onBindViewHolder");

        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public interface ItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final String LOG_TAG = WordViewHolder.class.getSimpleName() + " TEST";

        // Provide a reference to the views for each data item
        TextView wordTextView;

        public WordViewHolder(View itemView) {
            super(itemView);
            num_view_holders++;
            Log.d(LOG_TAG, "new WordViewHolder, nr: " + num_view_holders);

            wordTextView = itemView.findViewById(R.id.item_text);
            itemView.setOnClickListener(this);
        }

        private void bind(int number) {
            wordTextView.setText(model.get(number));
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            onClickListener.onListItemClick(clickedPosition);
        }
    }
}
