# ...por enquanto vazio, apenas para satisfazer a configuração do R8...
# Aqui você pode adicionar regras específicas se algo estiver sendo ofuscado indevidamente.

# Mantém anotações Kotlin (evita problemas com coroutines/reflection)
-keepattributes *Annotation*

# Coroutines (evita warnings e possíveis quebras em stacktraces)
-dontwarn kotlinx.coroutines.**

# Media3 / ExoPlayer (usado pela MediaPlayer-KMP)
-dontwarn androidx.media3.**
-keep class androidx.media3.** { *; }

# AndroidYouTubePlayer (core + custom-ui)
-dontwarn com.pierfrancescosoffritti.androidyoutubeplayer.**
-keep class com.pierfrancescosoffritti.androidyoutubeplayer.** { *; }

# Se você usar serialização baseada em reflexão (Gson/Moshi/serialization com reflection),
# é comum manter modelos de rede. Exemplo genérico (ajuste o seu package se necessário):
-keepclassmembers class com.walcker.flickly.** {
    @com.google.gson.annotations.SerializedName <fields>;
}
-keepclassmembers class com.walcker.flickly.** {
    @kotlinx.serialization.SerialName <fields>;
}
-keepclassmembers class com.walcker.flickly.** {
    @kotlinx.serialization.Serializable *;
}
-keep class com.walcker.flickly.**Models** { *; }

# Se começar a quebrar apenas em release, você pode, temporariamente, manter tudo de um módulo
# específico para debugar (exemplo):
# -keep class com.walcker.flickly.products.movies.** { *; }
