import { styled } from 'styled-components';

export const SettlementSubtabContainer = styled.div`
	* {
		font-family: 'Pretendard' !important;
	}

	display: flex;
	flex-direction: column;
	gap: 2rem;
	/* MUI Custom */
	.css-1h9z7r5-MuiButtonBase-root-MuiTab-root.Mui-selected {
		color: var(--main-color);
	}
	.css-1aquho2-MuiTabs-indicator {
		background-color: var(--main-color);
	}
`;
