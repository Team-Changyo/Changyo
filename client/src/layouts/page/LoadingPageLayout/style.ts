import { styled } from 'styled-components';

export const LoadingPageLayoutContainer = styled.div`
	position: relative;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	height: 100vh;
	background-color: var(--main-color);

	.changyo-logo {
		position: absolute;
		animation: risingLogo 2s;

		svg {
			width: 300px;
		}
	}
	.shinhan-bank-logo {
		position: absolute;
		bottom: 2rem;
		height: 30px;

		svg {
			width: 100px;
			height: 30px;

			path {
				fill: var(--white-color) !important;
			}
		}
	}
`;
