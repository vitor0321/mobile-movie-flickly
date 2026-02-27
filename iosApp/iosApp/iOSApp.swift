import AppMan
import FirebaseCore
import SwiftUI

@main
struct iOSApp: App {
    init() {
        FirebaseApp.configure()
        AppKoinKt.startKoinIfNeeded()
    }
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}