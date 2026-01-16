package com.walcker.flickly.products.movies.strings

internal data class MoviesListStrings(
    val appName: String = "",
    val popularMovies: String = "",
    val highlight: String = "",
    val topRatedMovies: String = "",
    val upcomingMovies: String = "",
    val invalidPasswordMessage: String = "",
    val passwordDialogTitle: String = "",
    val passwordDialogSubtitle: String = "",
    val passwordFieldLabel: String = "",
    val passwordConfirmLabel: String = "",
    val passwordDismissLabel: String = "",
)

internal val moviesListStringsPt = MoviesListStrings(
    appName = "Flickly",
    highlight = "Destaque",
    popularMovies = "Populares",
    topRatedMovies = "Mais Bem Avaliados",
    upcomingMovies = "Em Breve",
    invalidPasswordMessage = "Senha inválida",
    passwordDialogTitle = "Digite a senha",
    passwordDialogSubtitle = "Acesse o conteúdo de áudio",
    passwordFieldLabel = "Senha",
    passwordConfirmLabel = "Confirmar",
    passwordDismissLabel = "Cancelar",
)

internal val moviesListStringsEn = MoviesListStrings(
    appName = "Flickly",
    highlight = "Highlight",
    popularMovies = "Popular",
    topRatedMovies = "Top Rated",
    upcomingMovies = "Upcoming",
    invalidPasswordMessage = "Invalid password",
    passwordDialogTitle = "Enter password",
    passwordDialogSubtitle = "Access audio content",
    passwordFieldLabel = "Password",
    passwordConfirmLabel = "Confirm",
    passwordDismissLabel = "Cancel",
)

internal val moviesListStringsUr = MoviesListStrings(
    appName = "Flickly",
    highlight = "نمایاں",
    popularMovies = "مشہور",
    topRatedMovies = "اعلی درجہ بندی",
    upcomingMovies = "آنے والی",
    invalidPasswordMessage = "غلط پاس ورڈ",
    passwordDialogTitle = "پاس ورڈ درج کریں",
    passwordDialogSubtitle = "آڈیو مواد تک رسائی",
    passwordFieldLabel = "پاس ورڈ",
    passwordConfirmLabel = "تصدیق کریں",
    passwordDismissLabel = "منسوخ کریں",
)