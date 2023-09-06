import { styled } from 'styled-components';

export const AccountSelectorContainer = styled.div`
	li {
		display: flex;
		justify-content: space-between;
		align-items: center;
		flex-direction: row;
		position: relative;
		list-style: none;

		.account {
			display: flex;
			flex-direction: row;
			align-items: center;
			gap: 5px;
			svg {
				width: 20px;
				height: 20px;
			}

			span {
				color: var(--gray-400);
			}
		}
	}
`;
