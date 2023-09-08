import { styled } from 'styled-components';

export const NavbarLayoutWrapper = styled.aside`
	position: fixed;
	right: 0;
	left: 0;
	top: 0;
	z-index: 20;
`;

export const NavbarLayoutContainer = styled.nav`
	display: flex;
	align-items: center;
	width: 100%;
	max-width: 500px;
	height: 3rem;
	margin: 0 auto;
	background-color: var(--white-color);
`;
