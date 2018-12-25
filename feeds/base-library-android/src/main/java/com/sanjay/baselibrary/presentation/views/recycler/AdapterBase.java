package com.sanjay.baselibrary.presentation.views.recycler;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sanjay.baselibrary.app.utils.BaseUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sanjay
 * Base adapter to handler recycler view actions
 */

public abstract class AdapterBase<T> extends RecyclerView.Adapter<AdapterBase.ViewHolderBase> {

    public interface IAdapterBaseListener<T> {
        void onItemSelected(View view, List<T> list, int position);
    }

    @Nullable
    private RecyclerView recyclerView;
    protected IAdapterBaseListener<T> mListener;
    protected List<T> mList = new ArrayList<>();

    public AdapterBase() {}

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        this.recyclerView = null;
        super.onDetachedFromRecyclerView(recyclerView);
    }

    public void setListener(@Nullable IAdapterBaseListener<T> listener) {
        mListener = listener;
    }

    public void setList(List<T> list) {
        this.mList = list;
        notifyDataSetChanged();
        if (recyclerView != null) {
            recyclerView.scheduleLayoutAnimation();
        }
    }

    public void addList(List<T> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }


    @NonNull
    public List<T> getList() {
        if (mList == null) mList = new ArrayList<>();
        return mList;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @NonNull
    @Override
    public ViewHolderBase onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderBase<>(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), getItemResId(viewType), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterBase.ViewHolderBase holder, int position) {
        final int viewType = getItemViewType(position);

        if (!BaseUtils.INSTANCE.isEmpty(mList) && mList.size() > position) {
            holder.mBinding.setVariable(getVariableId(viewType), mList.get(position));
            onBindViewHolder(holder, position, viewType);
            holder.mBinding.executePendingBindings();
        }

        if (isItemClickable(position, viewType)) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final int position = holder.getAdapterPosition();
                    if (mListener != null && position != RecyclerView.NO_POSITION) {
                        mListener.onItemSelected(view, mList, position);
                    }
                }
            });
        }
    }

    protected void onBindViewHolder(ViewHolderBase holder, int position, int viewType) {
    }

    @LayoutRes
    protected abstract int getItemResId(int viewType);

    protected abstract int getVariableId(int viewType);

    protected boolean isItemClickable(int position, int viewType) {
        return true;
    }

    public static class ViewHolderBase<T extends ViewDataBinding> extends RecyclerView.ViewHolder {

        final public T mBinding;

        public ViewHolderBase(T binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }
    }
}
