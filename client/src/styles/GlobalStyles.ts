import { createGlobalStyle } from 'styled-components';
import reset from 'styled-reset';

export const GlobalStyles = createGlobalStyle`
    ${reset};

    :root {
        /* color */
        --main-color : #2449FF;
        --sub-color : #6B6B6F;

        --gray-500 : #6B6B6F;
        --gray-400 : #BEBEC4;
        --gray-300 : #EDEDEF;
        --gray-200 : #F8F8FA;
        --gray-100 : #f2f2f2;

        --black-500 : #000000;
        --black-400 : #4B4B4B;
        --black-300 : #3B3B3B;
        --black-200 : #2B2B2B;
        --black-100 : #1B1B1B;
    }

    body{
        padding: 0;
        margin: 0;
        font-family: 'Noto Sans KR', sans-serif;
    };
    button{
        display: flex;
        cursor: pointer;
        outline: none;
        border-radius: 3px;
    };
    input{
        display: flex;
        outline: none;
        padding-left: 10px;
    }

    // 스크롤 바
    ::-webkit-scrollbar {
        display: none;
    }
`;
