# RecyclerViewDrag
 RecyclerView拖拽移动，长按小图标拖拽，侧滑删除

长按小图标拖拽.gif
侧滑删除.gif
Android RecyclerView出来也很多年了，是非常成熟的控件，Github上工具一堆，做的非常全，侧滑、拖拽、动画都封装好的，但是工作需求，这种小功能就不用别人的，自己学习一下，写一个简单的代码即可实现。

RecyclerView的拖拽跟侧滑删除，其实就是使用 ItemTouchHelper 来实现，而我们只要写一下CallBack 继承 ItemTouchHelper.Callback()，重写里面的方法就行。

要注意的一点是，完成CallBack直接长按就能实现拖拽，但是点小图标拖拽，其实就是加个开关，item要使用ontouch回调来处理，而不要使用长按longClick回调，因为在三星手机上，onLongClick回调是在CallBack之后的，就会无法拖拽，而国产系统都是longClick先回调，坑了我一把。
