# ResourcesLoader [![](https://jitpack.io/v/DonaldDu/ResourcesLoader.svg)](https://jitpack.io/#DonaldDu/ResourcesLoader)

# 项目由来 
在学习qigsaw的过程中发现其加载和检查资源流程相对麻烦，从而造成效率低下。

加载新资源后不需要重复加载，所以加载资源调用次数非常少，基本都是检查逻辑。每次获取资源前都要检查Split是否加载，所以调用非常频繁，执行速度太慢则会影响流畅度。

启动一个DEMO项目（两个页面，SplashActivity：3View，MainActivity：9View），检查方法调用了338次。

qigsaw当前实现做一次检查大概250us（1ms=1000us），如果使用缓存基本可以达到25us（测试设备API30模拟器）。

# 新方案
为了提高运行速度，在以下方面做了优化：
- 尽可能少的使用反射。
- 检查期间尽可能少的NEW新对象（加载新资源时没管这个），减少GC。
- 探索更好、更稳定的隐藏API。

## 新发现

- API28+创建AssetManager是通过Builder来创建的，会加入系统（AssetManager.sSystem）AssetManager已加载的所有资源。如果把Split全部注入到系统AssetManager中，那么新Activity的AssetManager就自动包含了所有资源。虽然注释中说sSystem是共享的，但实际测试结果为：每个应用的sSystem是不同的。
- 基本上需要用到的隐藏API都可以通过特殊方式而直接调用。只有mStringBlocks需要的AssetManager.ensureStringBlocks()无法调用而必须反射。
- 通过对比加载资源的数量来得知是否已全部加载资源，替代复杂检查逻辑。


# 使用资源加载器

```
public interface IResourcesLoader {
    void initFastCompare(@NonNull AssetManager asset, @NonNull Collection<String> resPaths);

    /**
     * with pre inited resPaths
     */
    void loadResources(@NonNull AssetManager asset);
}
```

```
public class ResourcesLoader {
    @NonNull
    public static final IResourcesLoader instance;

    static {
        if (Build.VERSION.SDK_INT >= 28) {
            instance = new ResourcesLoader28();
        } else if (Build.VERSION.SDK_INT >= 21) {
            instance = new ResourcesLoader21();
        } else {
            throw new IllegalStateException("unsupported api");
        }
    }
}
```
提供了工具类，通过ResourcesLoader.instance直接调用。通过initFastCompare传入需要加载的apk资源，通过loadResources来检查并自动加载资源。

以下是自定义实现Qigsaw的SplitResourcesLoader来替换默认资源加载器的代码。
```
@AutoService(SplitResourcesLoader.class)
public class SplitResourcesLoaderCompat implements SplitResourcesLoader {

    @Override
    public void loadResources(@NonNull Context context, @NonNull Resources resources) {
        ResourcesLoader.instance.loadResources(resources.getAssets());
    }

    @Override
    public void loadResources(@NonNull Context context, @NonNull Resources preResources, @NonNull String splitApkPath) {
        ResourcesLoader.instance.initFastCompare(preResources.getAssets(), Collections.singleton(splitApkPath));
    }
}
```




