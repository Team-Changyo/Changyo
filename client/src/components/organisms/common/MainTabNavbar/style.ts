import { styled } from 'styled-components';

export const MainTabNavbarContainer = styled.div`
	width: 100%;
	display: flex;
	flex-direction: row;
	justify-content: space-between;
	align-items: center;
	padding: 0 15px;

	h1 {
		font-size: 1.5rem;
		font-weight: bold;
		color: var(--black-500);
	}

	.drop-down-menu {
		padding: 0;
		margin: 0;

		h1 {
			font-size: 1.5rem;
			font-weight: bold;
		}
	}
`;
