package com.example.android.news;

import android.animation.LayoutTransition;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by OWNER on 7/21/2016.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {

    private List<News> mNewses;
    private LayoutInflater mInflater;

    private ItemClickCallback mItemClickCallback;

    public interface ItemClickCallback {
        void onItemClick(int p);
    }

    public void setItemClickCallback(final ItemClickCallback itemClickCallback){
        mItemClickCallback = itemClickCallback;
    }


    public NewsAdapter(List<News> newses, Context c) {
        mInflater = LayoutInflater.from(c);
        this.mNewses = newses;
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method
     * should update the contents of the {@link ViewHolder#itemView} to reflect the item at
     * the given position.
     * <p/>
     * Note that unlike {@link ListView}, RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use {@link ViewHolder#getAdapterPosition()} which will
     * have the updated adapter position.
     * <p/>
     * Partial bind vs full bind:
     * <p/>
     * The payloads parameter is a merge list from {@link #notifyItemChanged(int, Object)} or
     * {@link #notifyItemRangeChanged(int, int, Object)}.  If the payloads list is not empty,
     * the ViewHolder is currently bound to old data and Adapter may run an efficient partial
     * update using the payload info.  If the payload is empty,  Adapter must run a full bind.
     * Adapter should not assume that the payload passed in notify methods will be received by
     * onBindViewHolder().  For example when the view is not attached to the screen, the
     * payload in notifyItemChange() will be simply dropped.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     * @param payloads A non-null list of merged payloads. Can be empty list if requires full
     */
    @Override
    public void onBindViewHolder(NewsHolder holder, int position) {
        News currentNews = mNewses.get(position);

        int backgroundColorID = 0;

        if (position % 2 != 0) {
            backgroundColorID = R.color.colorAccent;
        } else {
            backgroundColorID = R.color.normalBackground;
        }
        holder.mBackgroundLayout.setBackgroundColor(backgroundColorID);
        holder.mSectionTextView.setText(currentNews.getSection());
        holder.mTitleTextView.setText(currentNews.getTitle());
        holder.mDateTextView.setText(currentNews.getDate());

        if(currentNews.hasAuthor()){
            holder.mAuthorTextView.setText(currentNews.getAuthor());
            holder.mAuthorTextView.setVisibility(View.VISIBLE);
        }
        else{
            holder.mAuthorTextView.setVisibility(View.GONE);
        }
    }

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     * <p/>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p/>
     * The new ViewHolder will be used to display items of the adapter using
     * {@link #onBindViewHolder(ViewHolder, int, List)}. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary {@link View#findViewById(int)} calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(ViewHolder, int)
     */
    @Override
    public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.news_list, parent, false);
        return new NewsHolder(view);
    }

    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return mNewses.size();
    }

    class NewsHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private LinearLayout mBackgroundLayout;
        private TextView mSectionTextView;
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private TextView mAuthorTextView;

        public NewsHolder(View itemView){
            super(itemView);

            mBackgroundLayout = (LinearLayout)itemView.findViewById(R.id.background_linear);
            mBackgroundLayout.setOnClickListener(this);
            mSectionTextView = (TextView)itemView.findViewById(R.id.section_list);
            mTitleTextView = (TextView)itemView.findViewById(R.id.title_list);
            mDateTextView = (TextView)itemView.findViewById(R.id.date);
            mAuthorTextView = (TextView)itemView.findViewById(R.id.author);

        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.background_linear) {
                mItemClickCallback.onItemClick(getAdapterPosition());
            }
        }
    }
}