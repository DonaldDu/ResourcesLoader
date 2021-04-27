package android.content.res;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

public abstract class ApkAssets {
    @RequiresApi(api = 28)
    @NonNull
    public abstract String getAssetPath();
}
