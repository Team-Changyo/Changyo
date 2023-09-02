import { styled } from 'styled-components';

export const TabbarItemWrapper = styled.div<{ $isActive: boolean }>`
	display: flex;
	justify-content: center;
	align-items: center;
	width: calc(50% - 2.8rem);
	background-color: #fff;
	z-index: 30;

	&:hover {
		cursor: pointer;

		a {
			color: var(--main-color);
		}

		svg {
			fill: var(--main-color);
		}
	}

	a {
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		width: 100%;
		height: 100%;
		text-decoration: none;
		font-size: 0.7rem;
		font-weight: bold;
		color: ${({ $isActive }) => ($isActive ? 'var(--main-color)' : 'var(--sub-color)')};

		&:hover {
			color: var(--main-color);
		}
	}

	svg {
		width: 25px;
		height: 25px;
		margin-bottom: 5px;
		fill: ${({ $isActive }) => ($isActive ? 'var(--main-color)' : 'var(--sub-color)')};
	}
`;

export const TabbarCenterItemWrapper = styled.div`
	display: flex;
	justify-content: center;
	align-items: center;
	width: 5.6rem;
	z-index: 30;

	svg {
		width: 56px;
		height: 56px;
		position: absolute;
		left: 50%;
		-webkit-transform: translate(-50%, -15%);
		transform: translate(-50%, -15%);

		&:hover {
			cursor: pointer;
		}
	}
`;
