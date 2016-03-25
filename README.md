# AdvanceAdapter

> 可以添加Header和Footer的Adapter

* 低侵入性，采用装饰器的方式包裹正真的Adapter,无需修改原本Adapter
* 支持`LinearLayoutManager`、`GridLayoutManager`、`StaggeredGridLayoutManager`
* 可自定义Header和Footer的增加方式

## 注意

1. 在子Adapter中ViewHolder中调用getLayoutPosition()和getAdapterPosition()时会包含外层已添加的Header和Footer

2. 默认Header和Footer数量最大为100，且`ViewType`中的`Integer.MIN_VALUE`~`Integer.MIN_VALUE+100`、`Integer.MAX_VALUE-100`~`Integer.MAX_VALUE`均被占用

### 使用方法
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

### gradle引用方法
```
    compile 'com.zzhoujay.advanceadapter:advanceadapter:1.0.1'
```

### 运行效果
![演示截图](http://git.oschina.net/uploads/images/2015/0722/131020_f72dddbf_141009.png "演示截图")
![演示截图](http://git.oschina.net/uploads/images/2015/0722/131138_4e7f7269_141009.png "演示截图")


_by zzhoujay_