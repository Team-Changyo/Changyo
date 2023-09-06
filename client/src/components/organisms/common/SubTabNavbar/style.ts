import { styled } from 'styled-components';

export const SubTabNavbarContainer = styled.div`
	padding: 0 15px;
	width: 100%;
	position: relative;
	display: flex;
	flex-direction: row;
	justify-content: center;
	align-items: center;

	.back-btn {
		position: absolute;
		left: 5px; // TODO : 임시

		svg {
			width: 35px;
			height: 35px;
		}
	}

	h1 {
		font-size: 1.1rem;
		font-weight: bold;
	}
`;
