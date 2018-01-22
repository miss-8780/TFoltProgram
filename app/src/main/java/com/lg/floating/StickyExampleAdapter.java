package com.lg.floating;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2017/9/4.
 */
public class StickyExampleAdapter extends RecyclerView.Adapter<StickyExampleAdapter.RecyclerViewHolder> {
    //第一个吸顶
    private static final int FIRST_STICKY_VIEW = 1;
    //别的吸顶
    static final int HAS_STICKY_VIEW = 2;
    //正常View
    static final int NONE_STICKY_VIEW = 3;
    private final LayoutInflater mInflate;
    private final List<StickyBean> datas;

    StickyExampleAdapter(Context context, List<StickyBean> datas){
        mInflate = LayoutInflater.from(context);
        this.datas = datas;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View inflate = mInflate.inflate(R.layout.item_ui, parent, false);
        return new RecyclerViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        StickyBean stickyBean = datas.get(position);
        holder.tvName.setText(stickyBean.name);
        holder.tvGender.setText(stickyBean.autor);

        if (position == 0) {
            holder.tvStickyHeader.setVisibility(View.VISIBLE);
            holder.tvStickyHeader.setText(stickyBean.sticky);
            holder.itemView.setTag(FIRST_STICKY_VIEW);
        } else {
            if (!TextUtils.equals(stickyBean.sticky, datas.get(position - 1).sticky)) {
                holder.tvStickyHeader.setVisibility(View.VISIBLE);
                holder.tvStickyHeader.setText(stickyBean.sticky);
                holder.itemView.setTag(HAS_STICKY_VIEW);
            } else {
                holder.tvStickyHeader.setVisibility(View.GONE);
                holder.itemView.setTag(NONE_STICKY_VIEW);
            }
        }
        //通过此处设置ContentDescription，作为内容描述，可以通过getContentDescription取出，功效跟setTag差不多。
        holder.itemView.setContentDescription(stickyBean.sticky);
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        TextView tvStickyHeader;
        RelativeLayout rlContentWrapper;
        TextView tvName;
        TextView tvGender;
        RecyclerViewHolder(View itemView) {
            super(itemView);
            tvStickyHeader = (TextView) itemView.findViewById(R.id.tv_sticky_header_view);
            rlContentWrapper = (RelativeLayout) itemView.findViewById(R.id.rl_content_wrapper);
            tvName = (TextView) itemView.findViewById(R.id.name);
            tvGender = (TextView) itemView.findViewById(R.id.auto);
        }
    }
}
