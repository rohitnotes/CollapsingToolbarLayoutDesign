package collapsing.toolbar.layout.design;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

public class RecyclerViewAdapter  extends RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>
{
    private Context activity_context;
    private ArrayList<ItemModel> recyclerViewItemModelArrayList;

    public RecyclerViewAdapter(Context context)
    {
        this.activity_context = context;
    }

    public void feedData(ArrayList<ItemModel> recyclerViewItemModelArrayList)
    {
        this.recyclerViewItemModelArrayList = recyclerViewItemModelArrayList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(activity_context).inflate(R.layout.recycler_view_item_row, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, final int position) {

        ItemModel currentItem = recyclerViewItemModelArrayList.get(position);
        ((ItemViewHolder)itemViewHolder).setData(currentItem);
    }

    @Override
    public int getItemCount() {
        return (null != recyclerViewItemModelArrayList ? recyclerViewItemModelArrayList.size() : 0);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder
    {
        private TextView itemFirstTextView;
        private TextView itemSecondTextView;

        public ItemViewHolder(View itemView)
        {
            super(itemView);
            itemFirstTextView = itemView.findViewById(R.id.caller_name_text_view);
            itemSecondTextView = itemView.findViewById(R.id.call_time_text_view);
        }

        public void setData(ItemModel recyclerViewItemModel)
        {
           itemFirstTextView.setText(recyclerViewItemModel.getCallerName());
           itemSecondTextView.setText(recyclerViewItemModel.getCallTime());
        }
    }
}
