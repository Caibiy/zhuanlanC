package com.ycode.android.zhuanlanc.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:    yangjiadong
 * Time :     2016/8/5
 * Email:      caibiy666@gmail.com
 */
public  abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        protected  final String TAG=getClass().getSimpleName();
        protected  final Context mContext;
        protected  final LayoutInflater mLayInflater;
        public BaseRecyclerAdapter(Context context){
            this.mContext=context;
            mLayInflater=LayoutInflater.from(mContext);
        }
        protected  List<T>  mDataList=new ArrayList<>();
        //返回当前数据集合
        public List<T> getDataList(){return mDataList;}
        //返回指定位置数据
        public T getItemData(int position){
            return  position>0&&position<mDataList.size()?mDataList.get(position):null;
        }
        @Override
        public int getItemCount() {
            return mDataList==null?0:mDataList.size();
        }

        /**
        * 移除指定一条数据
         *
        * @param position 位置
        */
        public void removeItem(int position){
            if(position>0&&position<mDataList.size()){
                mDataList.remove(position);
                notifyItemRemoved(position);
            }
        }

    /**
     * 指定位置插入一条数据
     * @param t
     * @param position
     */
        public void addItem(T t,int position){
            mDataList.add(position,t);
            notifyItemInserted(position);
        }

    /**
     * 从最后的位置添加数据
     * @param t  数据
     */
    public void addItem(T t){
            addItem(t,mDataList.size());
        }
    //移除所有数据
    public void clearItems(){
            int size=mDataList.size();
            if(size>0){
                mDataList.clear();
                notifyItemRangeChanged(0,mDataList.size());
            }
    }

    /**
     * 批量插入数据
     * @param position
     * @param data
     */
    public void addItems(int position,List<T>data){
        if(position<=mDataList.size()&&data!=null&&data.size()>0){
            mDataList.addAll(position,data);
            notifyItemRangeChanged(position,data.size());
        }
    }

    /**
     * 批量插入数据
     * @param data
     */
    public void addItems(List<T>data){
        addItems(mDataList.size(),data);
    }
}
