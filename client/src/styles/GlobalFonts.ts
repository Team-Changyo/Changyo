import { createGlobalStyle } from 'styled-components';
import Pretendard100 from 'assets/fonts/Pretendard100.otf';
import Pretendard200 from 'assets/fonts/Pretendard200.otf';
import Pretendard300 from 'assets/fonts/Pretendard300.otf';
import Pretendard400 from 'assets/fonts/Pretendard400.otf';
import Pretendard500 from 'assets/fonts/Pretendard500.otf';
import Pretendard600 from 'assets/fonts/Pretendard600.otf';
import Pretendard700 from 'assets/fonts/Pretendard700.otf';
import Pretendard800 from 'assets/fonts/Pretendard800.otf';
import Pretendard900 from 'assets/fonts/Pretendard900.otf';

export const GlobalFonts = createGlobalStyle`
@font-face {
            font-family: "Pretendard";
            font-style: normal;
            font-weight: 100;
            src: url(${Pretendard100}) format("opentype");
        }
        @font-face {
            font-family: "Pretendard";
            font-style: normal;
            font-weight: 200;
            src: url(${Pretendard200}) format("opentype");
        }
        @font-face {
            font-family: "Pretendard";
            font-style: normal;
            font-weight: 300;
            src: url(${Pretendard300}) format("opentype");
        }
        @font-face {
            font-family: "Pretendard";
            font-style: normal;
            font-weight: 400;
            src: url(${Pretendard400}) format("opentype");
        }
        @font-face {
            font-family: "Pretendard";
            font-style: normal;
            font-weight: 500;
            src: url(${Pretendard500}) format("opentype");
        }
        @font-face {
            font-family: "Pretendard";
            font-style: normal;
            font-weight: 600;
            src: url(${Pretendard600}) format("opentype");
        }
        @font-face {
            font-family: "Pretendard";
            font-style: normal;
            font-weight: 700;
            src: url(${Pretendard700}) format("opentype");
        }
        @font-face {
            font-family: "Pretendard";
            font-style: normal;
            font-weight: 800;
            src: url(${Pretendard800}) format("opentype");
        }
        @font-face {
            font-family: "Pretendard";
            font-style: normal;
            font-weight: 900;
            src: url(${Pretendard900}) format("opentype");
        }
`;
