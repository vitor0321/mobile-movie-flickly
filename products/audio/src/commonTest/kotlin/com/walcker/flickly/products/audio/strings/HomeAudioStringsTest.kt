package com.walcker.flickly.products.audio.strings

import kotlin.test.Test
import kotlin.test.assertEquals

internal class HomeAudioStringsTest {

    @Test
    fun `given HomeAudioStrings when instantiated with default constructor then all fields are empty`() {
        val s = HomeAudioStrings()
        assertEquals("", s.titleToolbar); assertEquals("", s.chapter("1")); assertEquals("", s.peace)
        assertEquals("", s.books); assertEquals("", s.language); assertEquals("", s.oldTestament)
        assertEquals("", s.newTestament); assertEquals("", s.oldTestamentAll); assertEquals("", s.newTestamentAll)
        assertEquals("", s.allBooks); assertEquals("", s.ptName); assertEquals("", s.enName)
    }

    @Test
    fun `given homeAudioStringsPt when accessed then all texts in Portuguese are correct`() {
        val s = homeAudioStringsPt
        assertEquals("Bíblia em Áudio", s.titleToolbar)
        assertEquals("Capítulo 5", s.chapter("5")); assertEquals("0 capítulos", s.chaptersCount(0))
        assertEquals("3 capítulos", s.chaptersCount(3))
        assertEquals("Paz seja convosco", s.peace); assertEquals("Livros", s.books); assertEquals("Idioma", s.language)
        assertEquals("AT", s.oldTestament); assertEquals("NT", s.newTestament)
        assertEquals("Antigo Testamento", s.oldTestamentAll); assertEquals("Novo Testamento", s.newTestamentAll)
        assertEquals("Todos os Livros", s.allBooks)
        assertEquals("Português", s.ptName); assertEquals("English", s.enName); assertEquals("Urdu", s.urName)
        assertEquals("Sindi", s.sdName); assertEquals("Sindi (IN)", s.sdName)
    }

    @Test
    fun `given homeAudioStringsEn when accessed then all texts in English are correct`() {
        val s = homeAudioStringsEn
        assertEquals("Audio Bible", s.titleToolbar)
        assertEquals("Chapter 5", s.chapter("5")); assertEquals("3 chapters", s.chaptersCount(3))
        assertEquals("Peace be with you", s.peace); assertEquals("Books", s.books); assertEquals("Language", s.language)
        assertEquals("OT", s.oldTestament); assertEquals("NT", s.newTestament)
        assertEquals("Old Testament", s.oldTestamentAll); assertEquals("New Testament", s.newTestamentAll)
        assertEquals("All Books", s.allBooks)
        assertEquals("Português", s.ptName); assertEquals("English", s.enName); assertEquals("Urdu", s.urName)
        assertEquals("Sindi", s.sdName); assertEquals("Sindi (IN)", s.sdInName)
    }

    @Test
    fun `given homeAudioStringsUr when accessed then key texts in Urdu are correct`() {
        val s = homeAudioStringsUr
        assertEquals("آڈیو بائبل", s.titleToolbar); assertEquals("باب 5", s.chapter("5"))
        assertEquals("آپ پر سلامتی ہو", s.peace); assertEquals("کتابیں", s.books); assertEquals("زبان", s.language)
        assertEquals("عہدِ عتیق", s.oldTestament); assertEquals("عہدِ جدید", s.newTestament)
        assertEquals("تمام کتابیں", s.allBooks); assertEquals("3 ابواب", s.chaptersCount(3))
    }

    @Test
    fun `given homeAudioStringsPaIn when accessed then key texts in Punjabi IN are correct`() {
        val s = homeAudioStringsPaIn
        assertEquals("ਆਡੀਓ ਬਾਈਬਲ", s.titleToolbar); assertEquals("ਅਧਿਆਇ 5", s.chapter("5"))
        assertEquals("ਤੁਹਾਡੇ ਉੱਤੇ ਸ਼ਾਂਤੀ ਹੋਵੇ", s.peace); assertEquals("ਕਿਤਾਬਾਂ", s.books); assertEquals("ਭਾਸ਼ਾ", s.language)
        assertEquals("ਪੁਰਾਣਾ ਨਿਯਮ", s.oldTestament); assertEquals("ਨਵਾਂ ਨਿਯਮ", s.newTestament)
        assertEquals("ਸਾਰੀਆਂ ਕਿਤਾਬਾਂ", s.allBooks); assertEquals("3 ਅਧਿਆਇ", s.chaptersCount(3))
    }

    @Test
    fun `given homeAudioStringsPaPK when accessed then key texts in Punjabi PK are correct`() {
        val s = homeAudioStringsPaPK
        assertEquals("آڈیو بائبل", s.titleToolbar); assertEquals("باب 5", s.chapter("5"))
        assertEquals("تہاڈے اُتے سلامتی ہووے", s.peace); assertEquals("کتاباں", s.books); assertEquals("زبان", s.language)
        assertEquals("پرانا عہدنامہ", s.oldTestament); assertEquals("نواں عہدنامہ", s.newTestament)
        assertEquals("ساریاں کتاباں", s.allBooks); assertEquals("3 باب", s.chaptersCount(3))
    }

    @Test
    fun `given homeAudioStringsSdPk when accessed then key texts in Sindhi PK are correct`() {
        val s = homeAudioStringsSdPk
        assertEquals("آڊيو بائبل", s.titleToolbar); assertEquals("باب 5", s.chapter("5"))
        assertEquals("توهان تي سلامتي هجي", s.peace); assertEquals("ڪتابون", s.books); assertEquals("ٻولي", s.language)
        assertEquals("پراڻو عهد نامو", s.oldTestament); assertEquals("نئون عهد نامو", s.newTestament)
        assertEquals("سڀ ڪتاب", s.allBooks); assertEquals("3 باب", s.chaptersCount(3))
    }

    @Test
    fun `given homeAudioStringsSdIn when accessed then key texts in Sindhi IN are correct`() {
        val s = homeAudioStringsSdIn
        assertEquals("ऑडियो बाइबल", s.titleToolbar); assertEquals("अध्याय 5", s.chapter("5"))
        assertEquals("तुम्हां ते शांति हुजे", s.peace); assertEquals("पुस्तकें", s.books); assertEquals("भाषा", s.language)
        assertEquals("पुरानो नियम", s.oldTestament); assertEquals("नवो नियम", s.newTestament)
        assertEquals("सब पुस्तकें", s.allBooks); assertEquals("3 अध्याय", s.chaptersCount(3))
    }
}
