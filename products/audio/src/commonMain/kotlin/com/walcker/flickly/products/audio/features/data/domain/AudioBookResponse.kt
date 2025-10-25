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
    GENESIS(folderName = "genesis", toDomain = AudioBook.GENESIS),
    @SerialName("exodus")
    EXODUS(folderName = "exodus", toDomain = AudioBook.EXODUS),
    @SerialName("leviticus")
    LEVITICUS(folderName = "leviticus", toDomain = AudioBook.LEVITICUS),
    @SerialName("numbers")
    NUMBERS(folderName = "numbers", toDomain = AudioBook.NUMBERS),
    @SerialName("deuteronomy")
    DEUTERONOMY(folderName = "deuteronomy", toDomain = AudioBook.DEUTERONOMY),
    @SerialName("joshua")
    JOSHUA(folderName = "joshua", toDomain = AudioBook.JOSHUA),
    @SerialName("judges")
    JUDGES(folderName = "judges", toDomain = AudioBook.JUDGES),
    @SerialName("ruth")
    RUTH(folderName = "ruth", toDomain = AudioBook.RUTH),
    @SerialName("1-samuel")
    FIRST_SAMUEL(folderName = "1-samuel", toDomain = AudioBook.FIRST_SAMUEL),
    @SerialName("2-samuel")
    SECOND_SAMUEL(folderName = "2-samuel", toDomain = AudioBook.SECOND_SAMUEL),
    @SerialName("1-kings")
    FIRST_KINGS(folderName = "1-kings", toDomain = AudioBook.FIRST_KINGS),
    @SerialName("2-kings")
    SECOND_KINGS(folderName = "2-kings", toDomain = AudioBook.SECOND_KINGS),
    @SerialName("1-chronicles")
    FIRST_CHRONICLES(folderName = "1-chronicles", toDomain = AudioBook.FIRST_CHRONICLES),
    @SerialName("2-chronicles")
    SECOND_CHRONICLES(folderName = "2-chronicles", toDomain = AudioBook.SECOND_CHRONICLES),
    @SerialName("ezra")
    EZRA(folderName = "ezra", toDomain = AudioBook.EZRA),
    @SerialName("nehemiah")
    NEHEMIAH(folderName = "nehemiah", toDomain = AudioBook.NEHEMIAH),
    @SerialName("esther")
    ESTHER(folderName = "esther", toDomain = AudioBook.ESTHER),
    @SerialName("job")
    JOB(folderName = "job", toDomain = AudioBook.JOB),
    @SerialName("psalms")
    PSALMS(folderName = "psalms", toDomain = AudioBook.PSALMS),
    @SerialName("proverbs")
    PROVERBS(folderName = "proverbs", toDomain = AudioBook.PROVERBS),
    @SerialName("ecclesiastes")
    ECCLESIASTES(folderName = "ecclesiastes", toDomain = AudioBook.ECCLESIASTES),
    @SerialName("song-of-solomon")
    SONG_OF_SOLOMON(folderName = "song-of-solomon", toDomain = AudioBook.SONG_OF_SOLOMON),
    @SerialName("isaiah")
    ISAIAH(folderName = "isaiah", toDomain = AudioBook.ISAIAH),
    @SerialName("jeremiah")
    JEREMIAH(folderName = "jeremiah", toDomain = AudioBook.JEREMIAH),
    @SerialName("lamentations")
    LAMENTATIONS(folderName = "lamentations", toDomain = AudioBook.LAMENTATIONS),
    @SerialName("ezekiel")
    EZEKIEL(folderName = "ezekiel", toDomain = AudioBook.EZEKIEL),
    @SerialName("daniel")
    DANIEL(folderName = "daniel", toDomain = AudioBook.DANIEL),
    @SerialName("hosea")
    HOSEA(folderName = "hosea", toDomain = AudioBook.HOSEA),
    @SerialName("joel")
    JOEL(folderName = "joel", toDomain = AudioBook.JOEL),
    @SerialName("amos")
    AMOS(folderName = "amos", toDomain = AudioBook.AMOS),
    @SerialName("obadiah")
    OBADIAH(folderName = "obadiah", toDomain = AudioBook.OBADIAH),
    @SerialName("jonah")
    JONAH(folderName = "jonah", toDomain = AudioBook.JONAH),
    @SerialName("micah")
    MICAH(folderName = "micah", toDomain = AudioBook.MICAH),
    @SerialName("nahum")
    NAHUM(folderName = "nahum", toDomain = AudioBook.NAHUM),
    @SerialName("habakkuk")
    HABAKKUK(folderName = "habakkuk", toDomain = AudioBook.HABAKKUK),
    @SerialName("zephaniah")
    ZEPHANIAH(folderName = "zephaniah", toDomain = AudioBook.ZEPHANIAH),
    @SerialName("haggai")
    HAGGAI(folderName = "haggai", toDomain = AudioBook.HAGGAI),
    @SerialName("zechariah")
    ZECHARIAH(folderName = "zechariah", toDomain = AudioBook.ZECHARIAH),
    @SerialName("malachi")
    MALACHI(folderName = "malachi", toDomain = AudioBook.MALACHI),
    @SerialName("matthew")
    MATTHEW(folderName = "matthew", toDomain = AudioBook.MATTHEW),
    @SerialName("mark")
    MARK(folderName = "mark", toDomain = AudioBook.MARK),
    @SerialName("luke")
    LUKE(folderName = "luke", toDomain = AudioBook.LUKE),
    @SerialName("john")
    JOHN(folderName = "john", toDomain = AudioBook.JOHN),
    @SerialName("acts")
    ACTS(folderName = "acts", toDomain = AudioBook.ACTS),
    @SerialName("romans")
    ROMANS(folderName = "romans", toDomain = AudioBook.ROMANS),
    @SerialName("1-corinthians")
    FIRST_CORINTHIANS(folderName = "1-corinthians", toDomain = AudioBook.FIRST_CORINTHIANS),
    @SerialName("2-corinthians")
    SECOND_CORINTHIANS(folderName = "2-corinthians", toDomain = AudioBook.SECOND_CORINTHIANS),
    @SerialName("galatians")
    GALATIANS(folderName = "galatians", toDomain = AudioBook.GALATIANS),
    @SerialName("ephesians")
    EPHESIANS(folderName = "ephesians", toDomain = AudioBook.EPHESIANS),
    @SerialName("philippians")
    PHILIPPIANS(folderName = "philippians", toDomain = AudioBook.PHILIPPIANS),
    @SerialName("colossians")
    COLOSSIANS(folderName = "colossians", toDomain = AudioBook.COLOSSIANS),
    @SerialName("1-thessalonians")
    FIRST_THESSALONIANS(folderName = "1-thessalonians", toDomain = AudioBook.FIRST_THESSALONIANS),
    @SerialName("2-thessalonians")
    SECOND_THESSALONIANS(folderName = "2-thessalonians", toDomain = AudioBook.SECOND_THESSALONIANS),
    @SerialName("1-timothy")
    FIRST_TIMOTHY(folderName = "1-timothy", toDomain = AudioBook.FIRST_TIMOTHY),
    @SerialName("2-timothy")
    SECOND_TIMOTHY(folderName = "2-timothy", toDomain = AudioBook.SECOND_TIMOTHY),
    @SerialName("titus")
    TITUS(folderName = "titus", toDomain = AudioBook.TITUS),
    @SerialName("philemon")
    PHILEMON(folderName = "philemon", toDomain = AudioBook.PHILEMON),
    @SerialName("hebrews")
    HEBREWS(folderName = "hebrews", toDomain = AudioBook.HEBREWS),
    @SerialName("james")
    JAMES(folderName = "james", toDomain = AudioBook.JAMES),
    @SerialName("1-peter")
    FIRST_PETER(folderName = "1-peter", toDomain = AudioBook.FIRST_PETER),
    @SerialName("2-peter")
    SECOND_PETER(folderName = "2-peter", toDomain = AudioBook.SECOND_PETER),
    @SerialName("1-john")
    FIRST_JOHN(folderName = "1-john", toDomain = AudioBook.FIRST_JOHN),
    @SerialName("2-john")
    SECOND_JOHN(folderName = "2-john", toDomain = AudioBook.SECOND_JOHN),
    @SerialName("3-john")
    THIRD_JOHN(folderName = "3-john", toDomain = AudioBook.THIRD_JOHN),
    @SerialName("jude")
    JUDE(folderName = "jude", toDomain = AudioBook.JUDE),
    @SerialName("revelation")
    REVELATION(folderName = "revelation", toDomain = AudioBook.REVELATION),
}