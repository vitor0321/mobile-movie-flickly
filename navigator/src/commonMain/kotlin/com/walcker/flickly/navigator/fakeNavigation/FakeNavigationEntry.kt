package com.walcker.flickly.navigator.fakeNavigation

import cafe.adriel.voyager.core.screen.Screen
import com.walcker.flickly.navigator.AudioEntry
import com.walcker.flickly.navigator.MoviesEntry

public class FakeMoviesEntry : MoviesEntry {
    override fun moviesHome(): Screen {
        TODO("Not yet implemented")
    }

    override fun movieDetails(movieId: String): Screen {
        TODO("Not yet implemented")
    }
}

public class FakeAudioEntry : AudioEntry {
    override fun audioEntryPoint(): Screen {
        TODO("Not yet implemented")
    }

    override fun audioHome(): Screen {
        TODO("Not yet implemented")
    }
}