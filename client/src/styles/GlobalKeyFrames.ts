import { createGlobalStyle } from 'styled-components';

export const GlobalKeyFrames = createGlobalStyle`
    @keyframes risingLogo {
        from {
            margin-bottom: 0px;
        }
        to {
            margin-bottom: 30rem;
        }
    }

    @keyframes pulseSkeleton {
        0% {
             opacity: 1;
            }
        50% {
            opacity: 0.4;
        }
        100% {
            opacity: 1;
        }
    }
`;
