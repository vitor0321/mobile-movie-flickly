package com.walcker.flickly.navigator.fakeNavigation

import cafe.adriel.voyager.core.screen.Screen
import com.walcker.flickly.navigator.AudioDestination
import com.walcker.flickly.navigator.MoviesDestination

public class FakeMoviesDestination : MoviesDestination {
    override fun moviesHome(): Screen {
        TODO("Not yet implemented")
    }

    override fun movieDetails(movieId: String): Screen {
        TODO("Not yet implemented")
    }
}

public class FakeAudioDestination : AudioDestination {
    override fun audioEntryPoint(): Screen {
        TODO("Not yet implemented")
    }

    override fun audioHome(): Screen {
        TODO("Not yet implemented")
    }
}