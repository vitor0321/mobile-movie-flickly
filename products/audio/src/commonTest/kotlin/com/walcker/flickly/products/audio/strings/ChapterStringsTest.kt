package com.walcker.flickly.products.audio.strings

import kotlin.test.Test
import kotlin.test.assertEquals

internal class ChapterStringsTest {

    @Test
    fun `given ChapterStrings when instantiated with default constructor then all fields are empty`() {
        val s = ChapterStrings()
        assertEquals("", s.selectAChapterTo)
        assertEquals("", s.chaptersAvailable(10))
        assertEquals("", s.oldTestamentLabel)
        assertEquals("", s.newTestamentLabel)
    }

    @Test
    fun `given chapterStringsPt when accessed then all texts in Portuguese are correct`() {
        assertEquals("Selecione um capítulo para ouvir", chapterStringsPt.selectAChapterTo)
        assertEquals("5 capítulos disponíveis", chapterStringsPt.chaptersAvailable(5))
        assertEquals("0 capítulos disponíveis", chapterStringsPt.chaptersAvailable(0))
        assertEquals("Antigo Testamento", chapterStringsPt.oldTestamentLabel)
        assertEquals("Novo Testamento", chapterStringsPt.newTestamentLabel)
    }

    @Test
    fun `given chapterStringsEn when accessed then all texts in English are correct`() {
        assertEquals("Select a chapter to listen", chapterStringsEn.selectAChapterTo)
        assertEquals("5 chapters available", chapterStringsEn.chaptersAvailable(5))
        assertEquals("0 chapters available", chapterStringsEn.chaptersAvailable(0))
        assertEquals("Old Testament", chapterStringsEn.oldTestamentLabel)
        assertEquals("New Testament", chapterStringsEn.newTestamentLabel)
    }

    @Test
    fun `given chapterStringsUr when accessed then all texts in Urdu are correct`() {
        assertEquals("سننے کے لیے ایک باب منتخب کریں", chapterStringsUr.selectAChapterTo)
        assertEquals("3 ابواب دستیاب ہیں", chapterStringsUr.chaptersAvailable(3))
        assertEquals("عہدِ عتیق", chapterStringsUr.oldTestamentLabel)
        assertEquals("عہدِ جدید", chapterStringsUr.newTestamentLabel)
    }

    @Test
    fun `given chapterStringsPaIN when accessed then all texts in Punjabi IN are correct`() {
        assertEquals("ਸੁਣਨ ਲਈ ਇੱਕ ਅਧਿਆਇ ਚੁਣੋ", chapterStringsPaIN.selectAChapterTo)
        assertEquals("3 ਅਧਿਆਇ ਉਪਲਬਧ ਹਨ", chapterStringsPaIN.chaptersAvailable(3))
        assertEquals("عہدِ عتیق", chapterStringsPaIN.oldTestamentLabel)
        assertEquals("عہدِ جدید", chapterStringsPaIN.newTestamentLabel)
    }

    @Test
    fun `given chapterStringsPaPK when accessed then all texts in Punjabi PK are correct`() {
        assertEquals("سنن لئی اک باب منتخب کرو", chapterStringsPaPK.selectAChapterTo)
        assertEquals("3 باب دستیاب نیں", chapterStringsPaPK.chaptersAvailable(3))
        assertEquals("پرانا عہدنامہ", chapterStringsPaPK.oldTestamentLabel)
        assertEquals("نواں عہدنامہ", chapterStringsPaPK.newTestamentLabel)
    }

    @Test
    fun `given chapterStringsSdPk when accessed then all texts in Sindhi PK are correct`() {
        assertEquals("ٻڌڻ لاءِ هڪ باب چونڊيو", chapterStringsSdPk.selectAChapterTo)
        assertEquals("3 باب موجود آهن", chapterStringsSdPk.chaptersAvailable(3))
        assertEquals("پراڻو عهد نامو", chapterStringsSdPk.oldTestamentLabel)
        assertEquals("نئون عهد نامو", chapterStringsSdPk.newTestamentLabel)
    }

    @Test
    fun `given chapterStringsSdIn when accessed then all texts in Sindhi IN are correct`() {
        assertEquals("सुनने के लिए एक अध्याय चुनें", chapterStringsSdIn.selectAChapterTo)
        assertEquals("3 अध्याय उपलब्ध हैं", chapterStringsSdIn.chaptersAvailable(3))
        assertEquals("पुरानो नियम", chapterStringsSdIn.oldTestamentLabel)
        assertEquals("नवो नियम", chapterStringsSdIn.newTestamentLabel)
    }
}
