import { Inter } from "next/font/google";
import "./globals.css";
import Navbar from "./components/Navbar";

const inter = Inter({ subsets: ["latin"] });

export const metadata = {
  title: "PharmaVerify",
  description: "Solving counterfeit medicines using blockchain.",
};

export default function RootLayout({ children }) {

  return (
    <html lang="en" data-theme="retro">
      <body className={`${inter.className}`}>
        <Navbar/>
        {children}        
      </body>
    </html>
  );
}
