#AdvanceAdapter

> 可以添加Header和Footer的适配与RecyclerView的Adapter

* 可以添加多个Header和多个Footer
* 采用的是包裹原本的Adapter的方式，无需修改原本Adapter
* 同步子Adapter的数据改变事件

##注意：在子Adapter中ViewHolder中调用getLayoutPosition()和getAdapterPosition()时会包含外层已添加的Header和Footer

> 如果已经添加了一个Header，那么在子Adapter中调用getAdapterPosition()方法时返回的是该item在子Adapter中的位置+1

###使用方法
```java
    NormalAdapter normalAdapter = new NormalAdapter(msgs);
    advanceAdapter = new AdvanceAdapter(normalAdapter);
    View header = getLayoutInflater().inflate(R.layout.header, null);
    View header2 = getLayoutInflater().inflate(R.layout.header2, null);
    View footer = getLayoutInflater().inflate(R.layout.footer, null);
    View footer2 = getLayoutInflater().inflate(R.layout.footer2, null);
    advanceAdapter.addHeader(header);
    advanceAdapter.addHeader(header2);
    advanceAdapter.addFooter(footer);
    advanceAdapter.addFooter(footer2);
    recyclerView.setAdapter(advanceAdapter);
```

###运行效果
![演示截图](http://git.oschina.net/uploads/images/2015/0722/131020_f72dddbf_141009.png "演示截图")
![演示截图](http://git.oschina.net/uploads/images/2015/0722/131138_4e7f7269_141009.png "演示截图")


_by zzhoujay_