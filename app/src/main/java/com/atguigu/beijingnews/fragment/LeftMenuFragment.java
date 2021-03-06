package com.atguigu.beijingnews.fragment;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.atguigu.baselibrary.DensityUtil;
import com.atguigu.beijingnews.R;
import com.atguigu.beijingnews.activity.MainActivity;
import com.atguigu.beijingnews.base.BaseFragment;
import com.atguigu.beijingnews.bean.NewsCenterBean;
import com.atguigu.beijingnews.pager.NewsCenterPager;

import java.util.List;

/**
 * Created by Baby on 2017/2/5.
 * 左侧菜单
 */

public class LeftMenuFragment extends BaseFragment {
    private ListView listView;
    private LeftMenuFragmentAdapter adapter;
    //左侧菜单对应的数据
    private List<NewsCenterBean.DataBean> datas;
    private int prePosition = 0;

    @Override
    public View initView() {
        listView = new ListView(mContext);
        listView.setPadding(0, DensityUtil.dip2px(mContext,40),0,0);
        listView.setBackgroundColor(Color.CYAN);
        //设置监听
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //1.记录位置和刷新适配器
                prePosition = position;
                adapter.notifyDataSetChanged();//getCount-->getiew

                //2.关闭侧滑菜单
                MainActivity mainActivity = (MainActivity) mContext;
                mainActivity.getSlidingMenu().toggle();//关<->开

                //3.切换到对应的详情页面
                switchPager(prePosition);
            }
        });

        return listView;
    }

    private void switchPager(int prePosition) {
        MainActivity mainActivity = (MainActivity) mContext;
        ContentFragment contentFragment = mainActivity.getContentFragment();
        //得到新闻中心
        NewsCenterPager newsCenterPager = contentFragment.getNewsCenterPager();
        //调用新闻中心的切换详情页面的方法
        newsCenterPager.switchPager(prePosition);
    }

    @Override
    public void initData() {
        super.initData();
    }

    public void setData(List<NewsCenterBean.DataBean> dataBeanList) {
        this.datas = dataBeanList;

        //设置适配器
        adapter =new LeftMenuFragmentAdapter();
        listView.setAdapter(adapter);

        switchPager(prePosition);

    }

    class LeftMenuFragmentAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            TextView textView = (TextView) View.inflate(mContext, R.layout.item_leftmenu,null);

            //设置内容
            textView.setText(datas.get(position).getTitle());

            if(prePosition== position) {
                textView.setEnabled(true);
            }else{
                textView.setEnabled(true);
            }


            return textView;
        }
    }

}

