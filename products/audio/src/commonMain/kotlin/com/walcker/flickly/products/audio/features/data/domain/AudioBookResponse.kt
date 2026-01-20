package com.walcker.flickly.products.audio.features.data.domain

import com.walcker.flickly.products.audio.features.domain.model.AudioBook
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal enum class AudioBookResponse(
    val folderName: String,
    val toDomain: AudioBook,
) {
    @SerialName("genesis")
    Genesis(folderName = "genesis", toDomain = AudioBook.Genesis),
    @SerialName("exodus")
    Exodus(folderName = "exodus", toDomain = AudioBook.Exodus),
    @SerialName("leviticus")
    Leviticus(folderName = "leviticus", toDomain = AudioBook.Leviticus),
    @SerialName("numbers")
    Numbers(folderName = "numbers", toDomain = AudioBook.Numbers),
    @SerialName("deuteronomy")
    Deuteronomy(folderName = "deuteronomy", toDomain = AudioBook.Deuteronomy),
    @SerialName("joshua")
    Joshua(folderName = "joshua", toDomain = AudioBook.Joshua),
    @SerialName("judges")
    Judges(folderName = "judges", toDomain = AudioBook.Judges),
    @SerialName("ruth")
    Ruth(folderName = "ruth", toDomain = AudioBook.Ruth),
    @SerialName("1-samuel")
    FirstSamuel(folderName = "1-samuel", toDomain = AudioBook.FirstSamuel),
    @SerialName("2-samuel")
    SecondSamuel(folderName = "2-samuel", toDomain = AudioBook.SecondSamuel),
    @SerialName("1-kings")
    FirstKings(folderName = "1-kings", toDomain = AudioBook.FirstKings),
    @SerialName("2-kings")
    SecondKings(folderName = "2-kings", toDomain = AudioBook.SecondKings),
    @SerialName("1-chronicles")
    FirstChronicles(folderName = "1-chronicles", toDomain = AudioBook.FirstChronicles),
    @SerialName("2-chronicles")
    SecondChronicles(folderName = "2-chronicles", toDomain = AudioBook.SecondChronicles),
    @SerialName("ezra")
    Ezra(folderName = "ezra", toDomain = AudioBook.Ezra),
    @SerialName("nehemiah")
    Nehemiah(folderName = "nehemiah", toDomain = AudioBook.Nehemiah),
    @SerialName("esther")
    Esther(folderName = "esther", toDomain = AudioBook.Esther),
    @SerialName("job")
    Job(folderName = "job", toDomain = AudioBook.Job),
    @SerialName("psalms")
    Psalms(folderName = "psalms", toDomain = AudioBook.Psalms),
    @SerialName("proverbs")
    Proverbs(folderName = "proverbs", toDomain = AudioBook.Proverbs),
    @SerialName("ecclesiastes")
    Ecclesiastes(folderName = "ecclesiastes", toDomain = AudioBook.Ecclesiastes),
    @SerialName("song-of-solomon")
    SongOfSolomon(folderName = "song-of-solomon", toDomain = AudioBook.SongOfSolomon),
    @SerialName("isaiah")
    Isaiah(folderName = "isaiah", toDomain = AudioBook.Isaiah),
    @SerialName("jeremiah")
    Jeremiah(folderName = "jeremiah", toDomain = AudioBook.Jeremiah),
    @SerialName("lamentations")
    Lamentations(folderName = "lamentations", toDomain = AudioBook.Lamentations),
    @SerialName("ezekiel")
    Ezekiel(folderName = "ezekiel", toDomain = AudioBook.Ezekiel),
    @SerialName("daniel")
    Daniel(folderName = "daniel", toDomain = AudioBook.Daniel),
    @SerialName("hosea")
    Hosea(folderName = "hosea", toDomain = AudioBook.Hosea),
    @SerialName("joel")
    Joel(folderName = "joel", toDomain = AudioBook.Joel),
    @SerialName("amos")
    Amos(folderName = "amos", toDomain = AudioBook.Amos),
    @SerialName("obadiah")
    Obadiah(folderName = "obadiah", toDomain = AudioBook.Obadiah),
    @SerialName("jonah")
    Jonah(folderName = "jonah", toDomain = AudioBook.Jonah),
    @SerialName("micah")
    Micah(folderName = "micah", toDomain = AudioBook.Micah),
    @SerialName("nahum")
    Nahum(folderName = "nahum", toDomain = AudioBook.Nahum),
    @SerialName("habakkuk")
    Habakkuk(folderName = "habakkuk", toDomain = AudioBook.Habakkuk),
    @SerialName("zephaniah")
    Zephaniah(folderName = "zephaniah", toDomain = AudioBook.Zephaniah),
    @SerialName("haggai")
    Haggai(folderName = "haggai", toDomain = AudioBook.Haggai),
    @SerialName("zechariah")
    Zechariah(folderName = "zechariah", toDomain = AudioBook.Zechariah),
    @SerialName("malachi")
    Malachi(folderName = "malachi", toDomain = AudioBook.Malachi),
    @SerialName("matthew")
    Matthew(folderName = "matthew", toDomain = AudioBook.Matthew),
    @SerialName("mark")
    Mark(folderName = "mark", toDomain = AudioBook.Mark),
    @SerialName("luke")
    Luke(folderName = "luke", toDomain = AudioBook.Luke),
    @SerialName("john")
    John(folderName = "john", toDomain = AudioBook.John),
    @SerialName("acts")
    Acts(folderName = "acts", toDomain = AudioBook.Acts),
    @SerialName("romans")
    Romans(folderName = "romans", toDomain = AudioBook.Romans),
    @SerialName("1-corinthians")
    FirstCorinthians(folderName = "1-corinthians", toDomain = AudioBook.FirstCorinthians),
    @SerialName("2-corinthians")
    SecondCorinthians(folderName = "2-corinthians", toDomain = AudioBook.SecondCorinthians),
    @SerialName("galatians")
    Galatians(folderName = "galatians", toDomain = AudioBook.Galatians),
    @SerialName("ephesians")
    Ephesians(folderName = "ephesians", toDomain = AudioBook.Ephesians),
    @SerialName("philippians")
    Philippians(folderName = "philippians", toDomain = AudioBook.Philippians),
    @SerialName("colossians")
    Colossians(folderName = "colossians", toDomain = AudioBook.Colossians),
    @SerialName("1-thessalonians")
    FirstThessalonians(folderName = "1-thessalonians", toDomain = AudioBook.FirstThessalonians),
    @SerialName("2-thessalonians")
    SecondThessalonians(folderName = "2-thessalonians", toDomain = AudioBook.SecondThessalonians),
    @SerialName("1-timothy")
    FirstTimothy(folderName = "1-timothy", toDomain = AudioBook.FirstTimothy),
    @SerialName("2-timothy")
    SecondTimothy(folderName = "2-timothy", toDomain = AudioBook.SecondTimothy),
    @SerialName("titus")
    Titus(folderName = "titus", toDomain = AudioBook.Titus),
    @SerialName("philemon")
    Philemon(folderName = "philemon", toDomain = AudioBook.Philemon),
    @SerialName("hebrews")
    Hebrews(folderName = "hebrews", toDomain = AudioBook.Hebrews),
    @SerialName("james")
    James(folderName = "james", toDomain = AudioBook.James),
    @SerialName("1-peter")
    FirstPeter(folderName = "1-peter", toDomain = AudioBook.FirstPeter),
    @SerialName("2-peter")
    SecondPeter(folderName = "2-peter", toDomain = AudioBook.SecondPeter),
    @SerialName("1-john")
    FirstJohn(folderName = "1-john", toDomain = AudioBook.FirstJohn),
    @SerialName("2-john")
    SecondJohn(folderName = "2-john", toDomain = AudioBook.SecondJohn),
    @SerialName("3-john")
    ThirdJohn(folderName = "3-john", toDomain = AudioBook.ThirdJohn),
    @SerialName("jude")
    Jude(folderName = "jude", toDomain = AudioBook.Jude),
    @SerialName("revelation")
    Revelation(folderName = "revelation", toDomain = AudioBook.Revelation),
}