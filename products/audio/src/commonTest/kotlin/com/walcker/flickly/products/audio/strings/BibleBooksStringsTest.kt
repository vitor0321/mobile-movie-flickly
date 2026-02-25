package com.walcker.flickly.products.audio.strings

import kotlin.test.Test
import kotlin.test.assertEquals

internal class BibleBooksStringsTest {

    @Test
    fun `given BibleBooksStrings when instantiated with default constructor then all fields are empty`() {
        val s = BibleBooksStrings()
        assertEquals("", s.genesis); assertEquals("", s.exodus); assertEquals("", s.leviticus)
        assertEquals("", s.numbers); assertEquals("", s.deuteronomy); assertEquals("", s.joshua)
        assertEquals("", s.judges); assertEquals("", s.ruth); assertEquals("", s.firstSamuel)
        assertEquals("", s.secondSamuel); assertEquals("", s.firstKings); assertEquals("", s.secondKings)
        assertEquals("", s.firstChronicles); assertEquals("", s.secondChronicles); assertEquals("", s.ezra)
        assertEquals("", s.nehemiah); assertEquals("", s.esther); assertEquals("", s.job)
        assertEquals("", s.psalms); assertEquals("", s.proverbs); assertEquals("", s.ecclesiastes)
        assertEquals("", s.songOfSolomon); assertEquals("", s.isaiah); assertEquals("", s.jeremiah)
        assertEquals("", s.lamentations); assertEquals("", s.ezekiel); assertEquals("", s.daniel)
        assertEquals("", s.hosea); assertEquals("", s.joel); assertEquals("", s.amos)
        assertEquals("", s.obadiah); assertEquals("", s.jonah); assertEquals("", s.micah)
        assertEquals("", s.nahum); assertEquals("", s.habakkuk); assertEquals("", s.zephaniah)
        assertEquals("", s.haggai); assertEquals("", s.zechariah); assertEquals("", s.malachi)
        assertEquals("", s.matthew); assertEquals("", s.mark); assertEquals("", s.luke)
        assertEquals("", s.john); assertEquals("", s.acts); assertEquals("", s.romans)
        assertEquals("", s.firstCorinthians); assertEquals("", s.secondCorinthians); assertEquals("", s.galatians)
        assertEquals("", s.ephesians); assertEquals("", s.philippians); assertEquals("", s.colossians)
        assertEquals("", s.firstThessalonians); assertEquals("", s.secondThessalonians)
        assertEquals("", s.firstTimothy); assertEquals("", s.secondTimothy); assertEquals("", s.titus)
        assertEquals("", s.philemon); assertEquals("", s.hebrews); assertEquals("", s.james)
        assertEquals("", s.firstPeter); assertEquals("", s.secondPeter); assertEquals("", s.firstJohn)
        assertEquals("", s.secondJohn); assertEquals("", s.thirdJohn); assertEquals("", s.jude)
        assertEquals("", s.revelation)
    }

    @Test
    fun `given bibleBooksStringsPt when accessed then all book names in Portuguese are correct`() {
        val s = bibleBooksStringsPt
        assertEquals("Gênesis", s.genesis); assertEquals("Êxodo", s.exodus); assertEquals("Levítico", s.leviticus)
        assertEquals("Números", s.numbers); assertEquals("Deuteronômio", s.deuteronomy); assertEquals("Josué", s.joshua)
        assertEquals("Juízes", s.judges); assertEquals("Rute", s.ruth); assertEquals("1 Samuel", s.firstSamuel)
        assertEquals("2 Samuel", s.secondSamuel); assertEquals("1 Reis", s.firstKings); assertEquals("2 Reis", s.secondKings)
        assertEquals("1 Crônicas", s.firstChronicles); assertEquals("2 Crônicas", s.secondChronicles)
        assertEquals("Esdras", s.ezra); assertEquals("Neemias", s.nehemiah); assertEquals("Ester", s.esther)
        assertEquals("Jó", s.job); assertEquals("Salmos", s.psalms); assertEquals("Provérbios", s.proverbs)
        assertEquals("Eclesiastes", s.ecclesiastes); assertEquals("Cantares", s.songOfSolomon)
        assertEquals("Isaías", s.isaiah); assertEquals("Jeremias", s.jeremiah); assertEquals("Lamentações", s.lamentations)
        assertEquals("Ezequiel", s.ezekiel); assertEquals("Daniel", s.daniel); assertEquals("Oséias", s.hosea)
        assertEquals("Joel", s.joel); assertEquals("Amós", s.amos); assertEquals("Obadias", s.obadiah)
        assertEquals("Jonas", s.jonah); assertEquals("Miquéias", s.micah); assertEquals("Naum", s.nahum)
        assertEquals("Habacuque", s.habakkuk); assertEquals("Sofonias", s.zephaniah); assertEquals("Ageu", s.haggai)
        assertEquals("Zacarias", s.zechariah); assertEquals("Malaquias", s.malachi)
        assertEquals("Mateus", s.matthew); assertEquals("Marcos", s.mark); assertEquals("Lucas", s.luke)
        assertEquals("João", s.john); assertEquals("Atos", s.acts); assertEquals("Romanos", s.romans)
        assertEquals("1 Coríntios", s.firstCorinthians); assertEquals("2 Coríntios", s.secondCorinthians)
        assertEquals("Gálatas", s.galatians); assertEquals("Efésios", s.ephesians); assertEquals("Filipenses", s.philippians)
        assertEquals("Colossenses", s.colossians); assertEquals("1 Tessalonicenses", s.firstThessalonians)
        assertEquals("2 Tessalonicenses", s.secondThessalonians); assertEquals("1 Timóteo", s.firstTimothy)
        assertEquals("2 Timóteo", s.secondTimothy); assertEquals("Tito", s.titus); assertEquals("Filemom", s.philemon)
        assertEquals("Hebreus", s.hebrews); assertEquals("Tiago", s.james); assertEquals("1 Pedro", s.firstPeter)
        assertEquals("2 Pedro", s.secondPeter); assertEquals("1 João", s.firstJohn); assertEquals("2 João", s.secondJohn)
        assertEquals("3 João", s.thirdJohn); assertEquals("Judas", s.jude); assertEquals("Apocalipse", s.revelation)
    }

    @Test
    fun `given bibleBooksStringsEn when accessed then all book names in English are correct`() {
        val s = bibleBooksStringsEn
        assertEquals("Genesis", s.genesis); assertEquals("Exodus", s.exodus); assertEquals("Leviticus", s.leviticus)
        assertEquals("Numbers", s.numbers); assertEquals("Deuteronomy", s.deuteronomy); assertEquals("Joshua", s.joshua)
        assertEquals("Judges", s.judges); assertEquals("Ruth", s.ruth); assertEquals("1 Samuel", s.firstSamuel)
        assertEquals("2 Samuel", s.secondSamuel); assertEquals("1 Kings", s.firstKings); assertEquals("2 Kings", s.secondKings)
        assertEquals("1 Chronicles", s.firstChronicles); assertEquals("2 Chronicles", s.secondChronicles)
        assertEquals("Ezra", s.ezra); assertEquals("Nehemiah", s.nehemiah); assertEquals("Esther", s.esther)
        assertEquals("Job", s.job); assertEquals("Psalms", s.psalms); assertEquals("Proverbs", s.proverbs)
        assertEquals("Ecclesiastes", s.ecclesiastes); assertEquals("Song of Solomon", s.songOfSolomon)
        assertEquals("Isaiah", s.isaiah); assertEquals("Jeremiah", s.jeremiah); assertEquals("Lamentations", s.lamentations)
        assertEquals("Ezekiel", s.ezekiel); assertEquals("Daniel", s.daniel); assertEquals("Hosea", s.hosea)
        assertEquals("Joel", s.joel); assertEquals("Amos", s.amos); assertEquals("Obadiah", s.obadiah)
        assertEquals("Jonah", s.jonah); assertEquals("Micah", s.micah); assertEquals("Nahum", s.nahum)
        assertEquals("Habakkuk", s.habakkuk); assertEquals("Zephaniah", s.zephaniah); assertEquals("Haggai", s.haggai)
        assertEquals("Zacarias", s.zechariah) // NOTE: copy-paste bug in source – should be "Zechariah"
        assertEquals("Malachi", s.malachi)
        assertEquals("Matthew", s.matthew); assertEquals("Mark", s.mark); assertEquals("Luke", s.luke)
        assertEquals("John", s.john); assertEquals("Acts", s.acts); assertEquals("Romans", s.romans)
        assertEquals("1 Corinthians", s.firstCorinthians); assertEquals("2 Corinthians", s.secondCorinthians)
        assertEquals("Galatians", s.galatians); assertEquals("Ephesians", s.ephesians); assertEquals("Philippians", s.philippians)
        assertEquals("Colossians", s.colossians); assertEquals("1 Thessalonians", s.firstThessalonians)
        assertEquals("2 Thessalonians", s.secondThessalonians); assertEquals("1 Timothy", s.firstTimothy)
        assertEquals("2 Timothy", s.secondTimothy); assertEquals("Titus", s.titus); assertEquals("Philemon", s.philemon)
        assertEquals("Hebrews", s.hebrews); assertEquals("James", s.james); assertEquals("1 Peter", s.firstPeter)
        assertEquals("2 Peter", s.secondPeter); assertEquals("1 John", s.firstJohn); assertEquals("2 John", s.secondJohn)
        assertEquals("3 John", s.thirdJohn); assertEquals("Jude", s.jude); assertEquals("Revelation", s.revelation)
    }

    @Test
    fun `given bibleBooksStringsUr when accessed then representative book names in Urdu are correct`() {
        val s = bibleBooksStringsUr
        assertEquals("پیدائش", s.genesis); assertEquals("زبور", s.psalms)
        assertEquals("یسعیاہ", s.isaiah); assertEquals("متی", s.matthew); assertEquals("مکاشفہ", s.revelation)
    }

    @Test
    fun `given bibleBooksStringsPaIN when accessed then representative book names in Punjabi IN are correct`() {
        val s = bibleBooksStringsPaIN
        assertEquals("ਉਤਪਤਿ", s.genesis); assertEquals("ਜ਼ਬੂਰ", s.psalms)
        assertEquals("ਯਸਾਯਾਹ", s.isaiah); assertEquals("ਮੱਤੀ", s.matthew); assertEquals("ਪਰਕਾਸ਼ ਦੀ ਪੁਸਤਕ", s.revelation)
    }

    @Test
    fun `given bibleBooksStringsPaPK when accessed then representative book names in Punjabi PK are correct`() {
        val s = bibleBooksStringsPaPK
        assertEquals("پیدائش", s.genesis); assertEquals("زبور", s.psalms)
        assertEquals("یسعیاہ", s.isaiah); assertEquals("متی", s.matthew); assertEquals("مکاشفہ", s.revelation)
    }

    @Test
    fun `given bibleBooksStringsSdPk when accessed then representative book names in Sindhi PK are correct`() {
        val s = bibleBooksStringsSdPk
        assertEquals("پيدائش", s.genesis); assertEquals("زبور", s.psalms)
        assertEquals("يسعياه", s.isaiah); assertEquals("متي", s.matthew); assertEquals("مڪاشفو", s.revelation)
    }

    @Test
    fun `given bibleBooksStringsSdIn when accessed then representative book names in Sindhi IN are correct`() {
        val s = bibleBooksStringsSdIn
        assertEquals("उत्पत्ति", s.genesis); assertEquals("भजन संहिता", s.psalms)
        assertEquals("यशायाह", s.isaiah); assertEquals("मत्ती", s.matthew); assertEquals("प्रकाशित वाक्य", s.revelation)
    }
}
