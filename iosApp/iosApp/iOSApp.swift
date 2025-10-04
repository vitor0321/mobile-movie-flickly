import AppMan
import SwiftUI

@main
struct iOSApp: App {
    init() {
        AppKoinKt.startKoinIfNeeded()
    }
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}