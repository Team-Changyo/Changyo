import { createGlobalStyle } from 'styled-components';
import reset from 'styled-reset';

export const GlobalStyles = createGlobalStyle`
    ${reset};
    
    :root {
        /* color */
        --main-color : #2449FF;
        --sub-color : #6B6B6F;
        --danger-color : #ff2449;
        --white-color : #ffffff;

        --gray-500 : #444444;
        --gray-400 : #7e7e7e;
        --gray-300 : #d9d9d9;
        --gray-200 : #e4e8eb;
        --gray-100 : #f2f3f5;

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

    a{
        text-decoration: none;
        color: inherit;
    }
    *{
        box-sizing: border-box;
    }
    html, body, div, span, h1, h2, h3, h4, h5, h6, p, 
    a, dl, dt, dd, ol, ul, li, form, label, table{
        margin: 0;
        padding: 0;
        border: 0;
        font-size: 16px;
        vertical-align: baseline;
    }

    ol, ul{
        list-style: none;
    }
    button {
        font-family: 'Pretendard';
        border: 0;
        background: transparent;
        cursor: pointer;
    }
`;
