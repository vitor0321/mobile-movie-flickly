import AppMan
import UIKit
import SwiftUI

struct ComposeView: UIViewControllerRepresentable {
    var isDarkTheme: Bool

    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.mainViewController(isDarkTheme: isDarkTheme)
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
        MainViewControllerKt.updateTheme(controller: uiViewController, isDarkTheme: isDarkTheme)
    }
}

struct ContentView: View {
    @Environment(\.colorScheme) var colorScheme

    var body: some View {
        ComposeView(isDarkTheme: colorScheme == .dark)
            .ignoresSafeArea(.keyboard)
    }
}
