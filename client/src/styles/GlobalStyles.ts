import { createGlobalStyle } from 'styled-components';
import reset from 'styled-reset';

export const GlobalStyles = createGlobalStyle`
    ${reset};

    :root {
        /* color */
        --main-color : #2449FF;
        --sub-color : #6B6B6F;

        --gray-500 : #515457;
        --gray-400 : #6C6E70;
        --gray-300 : #ACACAD;
        --gray-100 : #F3F3F3;

        --black-500 : #000000;
        --black-300 : #3B3B3B;
        --black-100 : #2A2A2A;

        /* radius */
        --radius-s : 5px;
        --radius-m : 10px;
        --radius-l : 99px;
    }

    body{
        font-family: 'Pretendard';
        padding: 0;
        margin: 0;
    };

    // 스크롤 바
    ::-webkit-scrollbar {
        display: none;
    }
`;
