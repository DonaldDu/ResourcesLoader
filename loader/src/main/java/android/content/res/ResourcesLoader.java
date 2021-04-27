package android.content.res;

import android.os.Build;

import androidx.annotation.NonNull;

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
