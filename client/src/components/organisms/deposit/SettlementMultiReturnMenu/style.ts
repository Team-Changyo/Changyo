import { styled } from 'styled-components';

export const SettleMultiReturnMenuWrapper = styled.div`
	position: fixed;
	right: 0;
	left: 0;
	bottom: 0;
	z-index: 30;

	.multi-return-menu-container {
		display: flex;
		flex-direction: column;
		gap: 10px;
		background-color: var(--white-color);
		border: 2px solid var(--gray-100);
		border-radius: var(--radius-m);
		border-bottom: none;
		padding: 15px 15px;
		width: 100%;
		max-width: 500px;
		margin: 0 auto;
	}
`;
