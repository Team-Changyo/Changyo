import { styled } from 'styled-components';

export const TabWrapper = styled.aside`
	position: fixed;
	right: 0;
	left: 0;
	bottom: 0;
	z-index: 20;
`;

export const TabContainer = styled.nav`
	display: flex;
	width: 100%;
	max-width: 500px;
	height: 80px;
	margin: 0 auto;
`;

export const TabBackground = styled.div`
	position: absolute;
	left: 0;
	right: 0;
	bottom: 0;
	display: flex;
	width: 100%;
	align-items: baseline;

	#box {
		width: 40px;
		height: 64px;
		background-color: var(--white-color);
		z-index: 10;
	}

	&::before {
		content: '';
		display: inline-block;
		background: var(--white-color);
		width: 50%;
		height: 80px;
		box-shadow: 0 0 0.4rem 0 rgba(0, 0, 0, 0.16);
		border-radius: 0 2rem 0 0;
	}

	&::after {
		content: '';
		display: inline-block;
		background: var(--white-color);
		width: 50%;
		height: 80px;
		box-shadow: 0 0 0.4rem 0 rgba(0, 0, 0, 0.16);
		border-radius: 2rem 0 0 0;
	}
`;
