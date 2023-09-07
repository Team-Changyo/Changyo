import { styled } from 'styled-components';

export const AccountSelectListItemContainer = styled.li`
	display: flex;
	justify-content: space-between;
	align-items: center;
	flex-direction: row;
	position: relative;

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
	.change {
		display: flex;
	}
`;
